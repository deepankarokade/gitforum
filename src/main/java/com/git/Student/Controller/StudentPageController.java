package com.git.Student.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.git.Student.Entity.Student;
import com.git.Student.Repository.StudentRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Controller
public class StudentPageController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Forgot password - Send reset link
    @PostMapping("/student/sendforgot-password")
    public String handleForgotPassword(@RequestParam String email, RedirectAttributes redirectAttributes)
            throws MessagingException {
        // Check if email exists
        Student student = studentRepository.findByEmail(email).orElse(null);

        if (student == null) {
            redirectAttributes.addFlashAttribute("error", "Email not found");
            return "redirect:/student/forgot-password";
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String resetLink = "http://localhost:8081/student/change-password?email=" + email;

        helper.setTo(email);
        helper.setSubject("Reset Your Password");

        helper.setText("<h3>Password Reset</h3>"
                + "<p>Click the button below to reset your password:</p>"
                + "<a href='" + resetLink + "' "
                + "style='display:inline-block;padding:10px 16px;background:#2563eb;color:white;"
                + "text-decoration:none;border-radius:6px;font-weight:bold;'>"
                + "Change Password</a>"
                + "<p style='margin-top:10px;'>This link is valid for a limited time.</p>",
                true // enables HTML
        );

        mailSender.send(message);
        redirectAttributes.addFlashAttribute("info",
                "Password reset link has been sent to your registered email.");
        return "redirect:/student/login";
    }
}
