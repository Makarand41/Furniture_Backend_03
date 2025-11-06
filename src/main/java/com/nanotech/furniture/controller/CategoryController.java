package com.nanotech.furniture.controller;



import com.nanotech.furniture.entity.Category;
import com.nanotech.furniture.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category) {
        System.out.println("🔹 /api/category/add called — name: " + category.getName());
        return categoryService.addCategory(category);
    }

    

    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
