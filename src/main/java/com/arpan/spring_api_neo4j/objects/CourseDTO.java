package com.arpan.spring_api_neo4j.objects;

import com.arpan.spring_api_neo4j.models.Lesson;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {

    private String identifier;

    private String title;

    private String teacher;

    private List<Lesson> lessons = new ArrayList<>();

    private Boolean enrolled;

    public CourseDTO(String identifier, String title, String teacher) {
        this.identifier = identifier;
        this.title = title;
        this.teacher = teacher;
        this.enrolled = false;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public String getTeacher() {
        return teacher;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Boolean getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(Boolean enrolled) {
        this.enrolled = enrolled;
    }
}
