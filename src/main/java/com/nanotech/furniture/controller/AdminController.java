package com.nanotech.furniture.controller;

import com.nanotech.furniture.dto.AdminDTO;
import com.nanotech.furniture.dto.AdminResponseDTO;
import com.nanotech.furniture.entity.Admin;
import com.nanotech.furniture.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public Admin registerAdmin(@RequestBody AdminDTO adminDTO) {
        return adminService.registerAdmin(adminDTO);
    }

    @PostMapping("/login")
    public AdminResponseDTO loginAdmin(@RequestBody AdminDTO adminDTO) {
        return adminService.loginAdmin(adminDTO.getEmail(), adminDTO.getPassword());
    }

    @PostMapping("/logout")
    public String logout() {
        return "Logged out successfully (frontend only)";
    }

    @GetMapping("/check-session")
    public String checkSession() {
        return "Session handling disabled";
    }
}
