package com.git.Student.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.git.Student.Entity.Student;
import com.git.Student.enumactivity.ActivityStudent;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUid(String uid);

    // Count all students
    long countBy();

    // Count students by activity status
    long countByActivityStudent(ActivityStudent activityStudent);
}
