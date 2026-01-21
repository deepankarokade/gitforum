package com.git.Admin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/admin/login")
    public String loginPage() {
        return "admin/admin-login";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/admin-dashboard";
    }

    @GetMapping("/admin/profile")
    public String adminProfile() {
        return "admin/admin-manage-profile";
    }

    @GetMapping("/admin/dashboard/professor")
    public String manageProfessor() {
        return "admin/admin-manage-faculty";
    }

    @GetMapping("/admin/dashboard/professor/add")
    public String addProfessor() {
        return "admin/admin-add-faculty";
    }

    @GetMapping("/admin/dashboard/professor/edit/{id}")
    public String editProfessor(@PathVariable String id) {
        return "admin/admin-edit-faculty";
    }

    @GetMapping("/admin/dashboard/student")
    public String manageStudent() {
        return "admin/admin-manage-student";
    }

    @GetMapping("/admin/dashboard/student/add")
    public String addStudent() {
        return "admin/admin-student-registration";
    }

    @GetMapping("/admin/dashboard/student/edit")
    public String editStudent() {
        return "admin/admin-edit-student";
    }

    @GetMapping("/payment")
    public String paymentPage() {
        return "payment";
    }
}
