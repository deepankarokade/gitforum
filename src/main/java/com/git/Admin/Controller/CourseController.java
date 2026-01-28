package com.git.Admin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.git.Admin.Entity.Course;
import com.git.Admin.Service.CourseService;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // GET All Courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // ADD New Course
    @PostMapping
    public ResponseEntity<?> addNewCourse(@RequestBody CourseRequest request) {
        try {
            Course savedCourse = courseService.addNewCourse(
                    request.getCourseName(),
                    request.getCourseId(),
                    request.getSubjectIds());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // UPDATE Course
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseRequest request) {
        try {
            Course updatedCourse = courseService.updateCourse(
                    id,
                    request.getCourseName(),
                    request.getCourseId(),
                    request.isActive(),
                    request.getSubjectIds());
            return ResponseEntity.ok(updatedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // DTO for adding course with subjects
    public static class CourseRequest {
        private String courseName;
        private String courseId;
        private boolean active;
        private List<Long> subjectIds;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public List<Long> getSubjectIds() {
            return subjectIds;
        }

        public void setSubjectIds(List<Long> subjectIds) {
            this.subjectIds = subjectIds;
        }
    }

    // DELETE Course by courseId
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable String courseId) {
        try {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok("Course deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Total course count
    @GetMapping("/count/total")
    public long getTotalCourseCount() {
        return courseService.totalCoursesCount();
    }
}
