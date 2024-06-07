package com.arpan.spring_api_neo4j.services;

import com.arpan.spring_api_neo4j.models.Course;
import com.arpan.spring_api_neo4j.queryresults.CourseEnrollmentQueryResult;
import com.arpan.spring_api_neo4j.repositories.CourseRepository;
import com.arpan.spring_api_neo4j.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseEnrollmentService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public CourseEnrollmentService(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public Boolean getEnrollmentStatus(String username,String identifier){
        return userRepository.findEnrollmentStatus(username,identifier);
    }

    public CourseEnrollmentQueryResult enrollIn(String username,String identifier){
        // TODO make sure the user is not already enrolled in this course
        return userRepository.createEnrollmentRelationship(username,identifier);
    }

    public List<Course> getAllEnrolledCoursesByUsername(String username){
        return courseRepository.findAllEnrolledCoursesByUsername(username);
    }

}
