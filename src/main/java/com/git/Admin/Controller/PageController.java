package com.git.Admin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    // Home / Landing Page
    @GetMapping("/")
    public String homePage() {
        return "redirect:/landing-page.html";
    }

    // Student Registration (alternative route)
    @GetMapping("/student/register")
    public String studentRegister() {
        return "student/student-registration.html";
    }

    // Admin Login
    @GetMapping("/admin/login")
    public String loginPage() {
        return "admin/admin-login";
    }

    // Admin Dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/admin-dashboard";
    }

    // Admin Profile
    @GetMapping("/admin/profile")
    public String adminProfile() {
        return "admin/admin-manage-profile";
    }

    // Admin - Manage Professor
    @GetMapping("/admin/dashboard/professor")
    public String manageProfessor() {
        return "admin/admin-manage-faculty";
    }

    // Admin - Add Professor
    @GetMapping("/admin/dashboard/professor/add")
    public String addProfessor() {
        return "admin/admin-add-faculty";
    }

    // Admin - Edit Professor
    @GetMapping("/admin/dashboard/professor/edit/{id}")
    public String editProfessor(@PathVariable String id) {
        return "admin/admin-edit-faculty";
    }

    // Admin - Manage Student
    @GetMapping("/admin/dashboard/student")
    public String manageStudent() {
        return "admin/admin-manage-student";
    }

    // Admin - Manage course
    @GetMapping("/admin/dashboard/course")
    public String manageCourse() {
        return "admin/admin-manage-courses";
    }

    // Admin - Add Student
    @GetMapping("/admin/dashboard/student/add")
    public String addStudent() {
        return "admin/admin-student-registration";
    }

    // Admin - Edit Student
    @GetMapping("/admin/dashboard/student/edit")
    public String editStudent() {
        return "admin/admin-edit-student";
    }

    // Student Registration
    @GetMapping("/student/registration")
    public String studentRegistration() {
        return "student/student-registration.html";
    }

    // Payment Window
    @GetMapping("/payment")
    public String paymentPage() {
        return "payment";
    }

    // Admin Manage Exam
    @GetMapping("/admin/dashboard/exam")
    public String manageExam() {
        return "admin/admin-manage-exam";
    }

    // Admin Payments
    @GetMapping("/admin/dashboard/payment")
    public String managePayment() {
        return "admin/admin-payments";
    }

    // Student Login Page
    @GetMapping("/student/login")
    public String studentLogin() {
        return "student/Studentlogin";
    }

    // Student Login Page
    @GetMapping("/login-page")
    public String showLoginPage() {
        return "student/Studentlogin";
    }

    // Student Forgot Password Page
    @GetMapping("/student/forgot-password")
    public String studentForgotPassword() {
        return "student/studentForgotPassword";
    }

    // Student Reset Password Page (after login - requires current password)
    @GetMapping("/student/reset-password")
    public String studentResetPassword() {
        return "student/studentResetPassword";
    }

    // Student Change Password Page (from email link - no current password needed)
    @GetMapping("/student/change-password")
    public String studentChangePassword() {
        return "student/studentChangePassword";
    }

    // Student Dashboard
    @GetMapping("/student/dashboard")
    public String studentDashboard() {
        return "student/student-dashboard";
    }
}
