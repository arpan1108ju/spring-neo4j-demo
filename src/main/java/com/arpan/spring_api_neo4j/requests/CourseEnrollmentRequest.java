package com.arpan.spring_api_neo4j.requests;

public class CourseEnrollmentRequest {
    private String courseIdentifier;

    public CourseEnrollmentRequest() {
    }

    public String getCourseIdentifier() {
        return courseIdentifier;
    }

    public void setCourseIdentifier(String courseIdentifier) {
        this.courseIdentifier = courseIdentifier;
    }
}
