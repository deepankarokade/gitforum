package com.git.Admin.Service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.git.Admin.Entity.Admin;
import com.git.Admin.Repository.AdminRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminService {

	private final AdminRepository adminRepository;

	// Constructor
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	// Login - AdminController.java
	public Admin login(String uid, String password) {

		Admin admin = adminRepository.findByUid(uid)
				.orElseThrow(() -> new RuntimeException("Admin not found with UID " + uid));

		if (!admin.getPassword().equals(password)) {
			throw new RuntimeException("Invalid Password");
		}

		return admin;
	}

	// Get Profile - AdminProfileController.java
	public Admin getProfile(String uid) {
		return adminRepository.findByUid(uid)
				.orElseThrow(() -> new RuntimeException("Admin not found"));
	}

	// Change Password - AdminProfileController.java
	public void changePassword(String uid, String currentPassword, String newPassword) {

		Admin admin = adminRepository.findByUid(uid)
				.orElseThrow(() -> new RuntimeException("Admin not found"));

		if (!admin.getPassword().equals(currentPassword)) {
			throw new RuntimeException("Current password incorrect");
		}

		admin.setPassword(newPassword);
		adminRepository.save(admin);
	}

	// Get Admin UID - AdminProfileController.java
	public String getAdminUid(String uid) {

		return adminRepository.findByUid(uid)
				.orElseThrow(() -> new RuntimeException("Admin not found"))
				.getUid();
	}

	// Get Admin name - AdminProfileController.java
	public String getAdminName(String uid) {

		return adminRepository.findByUid(uid)
				.orElseThrow(() -> new RuntimeException("Admin not found"))
				.getName();
	}

	// Get Admin Phone number - AdminProfileController.java
	public Long getAdminPhoneNumber(String uid) {
		return adminRepository.findByUid(uid)
				.orElseThrow(() -> new RuntimeException("Admin not found"))
				.getPhoneNumber();
	}

	// Update Admin name - AdminProfileController.java
	public void updateAdminName(String uid, String name) {
		Admin admin = adminRepository.findByUid(uid)
				.orElseThrow(() -> new RuntimeException("Admin not found"));
		System.out.println("Before update: " + admin.getName());
		admin.setName(name);
		System.out.println("After update: " + admin.getName());
		adminRepository.save(admin);
	}

	// Update Admin Phone Number - AdminProfileController.java
	public void updatePhoneNumber(String uid, Long phoneNumber) {
		Admin admin = adminRepository.findByUid(uid)
				.orElseThrow(() -> new RuntimeException("Admin not found"));

		admin.setPhoneNumber(phoneNumber);

		adminRepository.save(admin);
	}

	// Get Admin Profile Pic
	public byte[] getProfilePicture(String uid) {
		Admin admin = adminRepository.findByUid(uid)
				.orElseThrow(() -> new RuntimeException("Admin not found"));

		if (admin.getProfileImage() == null) {
			throw new RuntimeException("No Profile Picture found");
		}

		return admin.getProfileImage();
	}

	// Update Profile Picture
	public void updateProfilePicture(String uid, MultipartFile file) {
		try {
			Admin admin = adminRepository.findByUid(uid)
					.orElseThrow(() -> new RuntimeException("Admin not found"));

			admin.setProfileImage(file.getBytes());

			adminRepository.save(admin);
		} catch (IOException e) {
			throw new RuntimeException("Failed to store profile image ", e);
		}
	}
}
