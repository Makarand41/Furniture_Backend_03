package com.nanotech.furniture.service;

import com.nanotech.furniture.dto.AdminDTO;
import com.nanotech.furniture.dto.AdminResponseDTO;
import com.nanotech.furniture.entity.Admin;
import com.nanotech.furniture.exception.ResourceAlreadyExistsException;
import com.nanotech.furniture.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Admin registerAdmin(AdminDTO dto) {
        if (adminRepository.findByEmail(dto.getEmail()) != null)
            throw new ResourceAlreadyExistsException("Email already exists!");
        if (adminRepository.findByUsername(dto.getUsername()) != null)
            throw new ResourceAlreadyExistsException("Username already exists!");

        Admin admin = new Admin();
        admin.setUsername(dto.getUsername());
        admin.setEmail(dto.getEmail());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        return adminRepository.save(admin);
    }
    
    public AdminResponseDTO loginAdmin(String email, String rawPassword) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) {
            throw new RuntimeException("Admin not found!");
        }

        if (!passwordEncoder.matches(rawPassword, admin.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        AdminResponseDTO response = new AdminResponseDTO();
        response.setId(admin.getId());
        response.setUsername(admin.getUsername());
        response.setEmail(admin.getEmail());
        return response;
    }


}
