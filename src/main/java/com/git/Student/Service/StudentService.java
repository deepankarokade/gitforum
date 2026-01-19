package com.git.Student.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.Student.Entity.Student;
import com.git.Student.Repository.StudentRepository;
import com.git.Student.enumactivity.ActivityStudent;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Save / Register Student
    public Student registerStudent(Student student) {
        // Generate and set the UID before saving
        String uid = generateStudentUid(student.getFullName(), student.getContactNumber());
        student.setUid(uid);
        return studentRepository.save(student);
    }

    // Generate Student Username
    private String generateStudentUid(String fullName, String mobileNumber) {

        if (fullName == null || fullName.length() <= 2) {
            throw new RuntimeException("Student name must have at least 2 characters");
        }

        if (mobileNumber == null || mobileNumber.length() < 5) {
            throw new RuntimeException("Mobile number must have at least 5 digits");
        }

        String namePart = fullName
                .trim()
                .substring(0, 2)
                .toUpperCase();

        String mobilePart = mobileNumber.substring(0, 5);

        return "STU-" + mobilePart + "-" + namePart;
    }

    // Fetch all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Fetch Student by UID
    public Student getStudentByUid(String uid) {
        return studentRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student editStudent(String uid, Student updatedStudent) {

        Student student = studentRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFullName(updatedStudent.getFullName());
        student.setGender(updatedStudent.getGender());
        student.setEmail(updatedStudent.getEmail());
        student.setContactNumber(updatedStudent.getContactNumber());
        student.setAddress(updatedStudent.getAddress());
        student.setState(updatedStudent.getState());
        student.setCity(updatedStudent.getCity());
        student.setParentContact(updatedStudent.getParentContact());
        student.setSchoolCollegeName(updatedStudent.getSchoolCollegeName());
        student.setStudentClass(updatedStudent.getStudentClass());
        student.setRegistrationId(updatedStudent.getRegistrationId());
        student.setPreferredExamDate(updatedStudent.getPreferredExamDate());
        student.setSubjects(updatedStudent.getSubjects());

        return studentRepository.save(student);
    }

    // Update Student Photo
    public Student updateStudentPhoto(String uid, byte[] photo, String photoFilename) {
        Student student = studentRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setPhoto(photo);
        student.setPhotofilename(photoFilename);

        return studentRepository.save(student);
    }

    // GET Student Activity Status
    public ActivityStudent getAccountStatus(String uid) {
        return studentRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("Student not found"))
                .getActivityStudent();
    }

    // UPDATE Student Activity Status
    public void updateStudentActivityStatus(String uid, ActivityStudent activity) {
        Student student = studentRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setActivityStudent(activity);
        studentRepository.save(student);
    }

    // Get total student count
    public long getTotalStudentCount() {
        return studentRepository.count();
    }

    // Get active student count
    public long getActiveStudentCount() {
        return studentRepository.countByActivityStudent(ActivityStudent.ACTIVE);
    }

}
