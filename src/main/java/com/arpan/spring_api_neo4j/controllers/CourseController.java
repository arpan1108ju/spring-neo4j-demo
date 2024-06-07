package com.arpan.spring_api_neo4j.controllers;

import com.arpan.spring_api_neo4j.models.Course;
import com.arpan.spring_api_neo4j.objects.CourseDTO;
import com.arpan.spring_api_neo4j.services.CourseEnrollmentService;
import com.arpan.spring_api_neo4j.services.CourseService;
import com.arpan.spring_api_neo4j.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseEnrollmentService courseEnrollmentService;

    public CourseController(CourseService courseService, LessonService lessonService,
                            CourseEnrollmentService courseEnrollmentService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.courseEnrollmentService = courseEnrollmentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> courseIndex(Principal principal) {
//        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);

        List<Course> courses = courseService.getAllCourses();

        List<CourseDTO> responseCourses = courses.stream().map(
                (course) -> {
                    CourseDTO responseCourse = new CourseDTO(course.getIdentifier(),course.getTitle(),course.getTeacher());
                    responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(responseCourse.getIdentifier()));

                    if(principal != null){
                        responseCourse.setEnrolled(courseEnrollmentService.getEnrollmentStatus(
                                principal.getName(), course.getIdentifier())
                        );
                    }


                    return responseCourse;
                }
        ).collect(Collectors.toList());

        return new ResponseEntity<>(responseCourses,HttpStatus.OK);

    }

    @GetMapping("/{identifier}")
    public ResponseEntity<CourseDTO> courseDetails(@PathVariable("identifier") String identifier){
        Course course = courseService.getCourseByIdentifier(identifier);

        CourseDTO responseCourse = new CourseDTO(course.getIdentifier(),course.getTitle(),course.getTeacher());

        responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(responseCourse.getIdentifier()));

        return new ResponseEntity<>(responseCourse,HttpStatus.OK);
    }


}
