package com.nanotech.furniture.repository;



import com.nanotech.furniture.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByNameIgnoreCase(String name);
}
