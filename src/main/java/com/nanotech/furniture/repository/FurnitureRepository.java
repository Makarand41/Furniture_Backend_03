package com.nanotech.furniture.repository;

import com.nanotech.furniture.entity.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
}
