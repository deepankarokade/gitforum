package com.git.Admin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/admin/login")
    public String loginPage() {
        return "admin-login"; // admin-login.html
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/admin/profile")
    public String adminProfile() {
        return "admin-manage-profile";
    }

    @GetMapping("/admin/dashboard/professor")
    public String manageProfessor() {
        return "admin-manage-faculty";
    }

    @GetMapping("/admin/dashboard/professor/add")
    public String addProfessor() {
        return "admin-add-faculty";
    }

    @GetMapping("/admin/dashboard/professor/edit/{id}")
    public String editProfessor(@PathVariable String id) {
        return "admin-edit-faculty";
    }
}
