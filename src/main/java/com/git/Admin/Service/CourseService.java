package com.git.Admin.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.Admin.Entity.Course;
import com.git.Admin.Entity.Subject;
import com.git.Admin.Repository.CourseRepository;
import com.git.Admin.Repository.SubjectRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    // GET all Course
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // ADD new Course
    public Course addNewCourse(String courseName, String courseId, List<Long> subjectIds) {

        if (courseRepository.existsByCourseName(courseName)) {
            throw new RuntimeException("Course Already Exists");
        } else if (courseRepository.existsByCourseId(courseId)) {
            throw new RuntimeException("CourseId Exists");
        }

        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseId(courseId);

        if (subjectIds != null && !subjectIds.isEmpty()) {
            List<Subject> selectedSubjects = subjectRepository.findAllById(subjectIds);
            course.setSubjects(selectedSubjects);
        }

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

    // UPDATE Course
    public Course updateCourse(long id, String courseName, String courseId, boolean active, List<Long> subjectIds) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course Not found"));

        // Check if name/id already exists in other courses
        if (!course.getCourseName().equals(courseName) && courseRepository.existsByCourseName(courseName)) {
            throw new RuntimeException("Course Name Already Exists");
        }
        if (!course.getCourseId().equals(courseId) && courseRepository.existsByCourseId(courseId)) {
            throw new RuntimeException("Course ID Already Exists");
        }

        course.setCourseName(courseName);
        course.setCourseId(courseId);
        course.setActive(active);

        if (subjectIds != null) {
            List<Subject> selectedSubjects = subjectRepository.findAllById(subjectIds);
            course.setSubjects(selectedSubjects);
        }

        return courseRepository.save(course);
    }
}
