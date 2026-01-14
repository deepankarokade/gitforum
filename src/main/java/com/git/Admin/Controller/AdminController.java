package com.git.Admin.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.git.Admin.Entity.Admin;
import com.git.Admin.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody Admin admin) {

        Admin loggedInAdmin =
                adminService.login(admin.getUid(), admin.getPassword());

        return ResponseEntity.ok(loggedInAdmin);
    }
}
