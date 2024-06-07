package com.arpan.spring_api_neo4j.controllers;

import com.arpan.spring_api_neo4j.models.Course;
import com.arpan.spring_api_neo4j.objects.CourseDTO;
import com.arpan.spring_api_neo4j.services.CourseService;
import com.arpan.spring_api_neo4j.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;
    private final LessonService lessonService;

    public CourseController(CourseService courseService, LessonService lessonService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Course>> courseIndex() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<CourseDTO> courseDetails(@PathVariable("identifier") String identifier){
        Course course = courseService.getCourseByIdentifier(identifier);

        CourseDTO responseCourse = new CourseDTO(course.getIdentifier(),course.getTitle(),course.getTeacher());

        responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(responseCourse.getIdentifier()));

        return new ResponseEntity<>(responseCourse,HttpStatus.OK);
    }


}
