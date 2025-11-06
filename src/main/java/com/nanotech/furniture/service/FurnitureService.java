package com.nanotech.furniture.service;

import com.nanotech.furniture.entity.Furniture;
import com.nanotech.furniture.entity.Category;
import com.nanotech.furniture.entity.FurnitureImage;
import com.nanotech.furniture.repository.CategoryRepository;
import com.nanotech.furniture.repository.FurnitureImageRepository;
import com.nanotech.furniture.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FurnitureService {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FurnitureImageRepository furnitureImageRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public Furniture addFurniture(Furniture furniture,
                                  MultipartFile mainImage,
                                  MultipartFile[] images) throws IOException {

        Category category = categoryRepository.findById(furniture.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        furniture.setCategory(category);

        // save main image
        if (mainImage != null && !mainImage.isEmpty()) {
            String mainPath = saveFile(mainImage);
            furniture.setImage(mainPath);
        }

        Furniture savedFurniture = furnitureRepository.save(furniture);

        // save multiple images
        if (images != null) {
            List<FurnitureImage> imageList = new ArrayList<>();
            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    FurnitureImage fi = new FurnitureImage();
                    fi.setImage(saveFile(file));
                    fi.setFurniture(savedFurniture);
                    imageList.add(fi);
                }
            }
            furnitureImageRepository.saveAll(imageList);
        }

        return savedFurniture;
    }

    private String saveFile(MultipartFile file) throws IOException {
        File uploadDir = new File(System.getProperty("user.dir") + File.separator + "uploads");
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(uploadDir, fileName);
        file.transferTo(dest);
        return "uploads/" + fileName;
    }

    public List<Furniture> getAllFurnitures() {
        return furnitureRepository.findAll();
    }

    public Furniture getFurnitureById(Long id) {
        return furnitureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Furniture not found with id: " + id));
    }

    public void deleteFurnitureById(Long id) {
        furnitureRepository.deleteById(id);
    }

    public Furniture updateFurniture(Long id,
                                     String name,
                                     String description,
                                     String height,
                                     String width,
                                     Long categoryId,
                                     MultipartFile mainImage,
                                     MultipartFile[] images) throws IOException {

        Furniture existing = furnitureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Furniture not found with ID: " + id));

        existing.setName(name);
        existing.setDescription(description);
        existing.setHeight(height != null ? height : existing.getHeight());
        existing.setWidth(width != null ? width : existing.getWidth());

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setCategory(category);

        if (mainImage != null && !mainImage.isEmpty()) {
            String path = saveFile(mainImage);
            existing.setImage(path);
        }

        if (images != null && images.length > 0) {
            List<FurnitureImage> newImages = new ArrayList<>();
            for (MultipartFile img : images) {
                if (!img.isEmpty()) {
                    FurnitureImage fi = new FurnitureImage();
                    fi.setImage(saveFile(img));
                    fi.setFurniture(existing);
                    newImages.add(fi);
                }
            }
            furnitureImageRepository.saveAll(newImages);
        }

        return furnitureRepository.save(existing);
    }
}
