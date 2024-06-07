package com.arpan.spring_api_neo4j.controllers;

import com.arpan.spring_api_neo4j.models.Course;
import com.arpan.spring_api_neo4j.objects.CourseDTO;
import com.arpan.spring_api_neo4j.objects.CourseEnrollmentDTO;
import com.arpan.spring_api_neo4j.queryresults.CourseEnrollmentQueryResult;
import com.arpan.spring_api_neo4j.requests.CourseEnrollmentRequest;
import com.arpan.spring_api_neo4j.services.CourseEnrollmentService;
import com.arpan.spring_api_neo4j.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class CourseEnrollmentController {

    private final CourseEnrollmentService courseEnrollmentService;
    private final LessonService lessonService;

    public CourseEnrollmentController(CourseEnrollmentService courseEnrollmentService, LessonService lessonService) {
        this.courseEnrollmentService = courseEnrollmentService;
        this.lessonService = lessonService;
    }


    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> enrollments(Principal principal){
        List<Course> courses =  courseEnrollmentService.getAllEnrolledCoursesByUsername(principal.getName());
        List<CourseDTO> responseCourses = courses.stream().map(
                (course) -> {
                    CourseDTO courseDTO = new CourseDTO(course.getIdentifier(),course.getTitle(),course.getTeacher());
                    courseDTO.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
                    courseDTO.setEnrolled(true);
                    return courseDTO;
                }
        ).toList();

        return new ResponseEntity<>(responseCourses,HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<CourseEnrollmentDTO> enrollIn(@RequestBody CourseEnrollmentRequest request, Principal principal){
        CourseEnrollmentQueryResult courseEnrollmentQueryResult = courseEnrollmentService.enrollIn(principal.getName(),
                request.getCourseIdentifier());

        CourseEnrollmentDTO responseEnrollment = new CourseEnrollmentDTO(
                courseEnrollmentQueryResult.getUser().getUsername(),
                courseEnrollmentQueryResult.getUser().getName(),
                courseEnrollmentQueryResult.getCourse());

        return new ResponseEntity<>(responseEnrollment , HttpStatus.OK);

    }



}
