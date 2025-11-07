package com.nanotech.furniture.repository;

import com.nanotech.furniture.entity.Furniture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
	List<Furniture> findByCategoryId(Long categoryId);
}
