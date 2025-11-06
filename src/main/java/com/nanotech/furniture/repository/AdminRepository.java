package com.nanotech.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nanotech.furniture.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
    Admin findByUsername(String username);
}
