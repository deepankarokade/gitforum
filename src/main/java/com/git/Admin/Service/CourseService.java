package com.git.Admin.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.Admin.Entity.Course;
import com.git.Admin.Repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // GET all Course
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // ADD new Course
    public Course addNewCourse(String courseName, String courseId, Course course) {

        if (courseRepository.existsByCourseName(courseName)) {
            throw new RuntimeException("Course Already Exists");
        } else if (courseRepository.existsByCourseId(courseId)) {
            throw new RuntimeException("CourseId Exists");
        }

        course.setCourseName(courseName);
        course.setCourseId(courseId);

        return courseRepository.save(course);
    }

    // DELETE Course
    public void deleteCourse(String courseId) {
        Course course = courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new RuntimeException("Course Not found"));

        courseRepository.delete(course);
    }

    // GET Total Courses Count
    public long totalCoursesCount() {
        return courseRepository.count();
    }
}
