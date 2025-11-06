//package com.nanotech.furniture.service;
//
//import com.nanotech.furniture.entity.Category;
//import com.nanotech.furniture.repository.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CategoryService {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    public Category addCategory(Category category) {
//    	if (categoryRepository.findByNameIgnoreCase(category.getName()) != null) {
//    	    throw new RuntimeException("Category already exists!");
//    	}
//
//        return categoryRepository.save(category);
//    }
//
//    public List<Category> getAllCategories() {
//        return categoryRepository.findAll();
//    }
//}
package com.nanotech.furniture.service;

import com.nanotech.furniture.entity.Category;
import com.nanotech.furniture.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        System.out.println("🔹 Checking category: " + category.getName());
        Category existing = categoryRepository.findByNameIgnoreCase(category.getName());
        System.out.println("🔹 Found existing: " + existing);
        if (existing != null) {
            System.out.println("⚠️ Category already exists!");
            throw new RuntimeException("Category already exists!");
        }
        Category saved = categoryRepository.save(category);
        System.out.println("✅ Category saved: " + saved.getId() + " - " + saved.getName());
        return saved;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        System.out.println("Retrieved categories count: " + categories.size());
        return categories;
    }
}
