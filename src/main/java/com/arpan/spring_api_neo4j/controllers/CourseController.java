package com.arpan.spring_api_neo4j.controllers;

import com.arpan.spring_api_neo4j.models.Course;
import com.arpan.spring_api_neo4j.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Course>> courseIndex() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Course> courseDetails(@PathVariable("identifier") String identifier){
        Course course = courseService.getCourseByIdentifier(identifier);

        return new ResponseEntity<>(course,HttpStatus.OK);
    }


}
