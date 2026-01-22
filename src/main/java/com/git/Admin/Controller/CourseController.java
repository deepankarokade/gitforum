package com.git.Admin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @PostMapping("/add")
    public ResponseEntity<?> addNewCourse(
            @RequestParam("courseName") String courseName,
            @RequestParam("courseId") String courseId) {
        try {
            Course course = new Course();
            Course savedCourse = courseService.addNewCourse(courseName, courseId, course);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ADD New Course with Request Body
    @PostMapping
    public ResponseEntity<?> addNewCourseWithBody(@RequestBody Course course) {
        try {
            Course savedCourse = courseService.addNewCourse(
                    course.getCourseName(),
                    course.getCourseId(),
                    course);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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
