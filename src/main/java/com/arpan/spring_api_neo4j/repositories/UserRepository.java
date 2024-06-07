package com.arpan.spring_api_neo4j.repositories;

import com.arpan.spring_api_neo4j.models.User;
import com.arpan.spring_api_neo4j.queryresults.CourseEnrollmentQueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User,Long> {
    Optional<User> findUserByUsername(String username);

    @Query("MATCH (user:User),(course:Course) WHERE user.username = $username and course.identifier = $identifier" +
            " RETURN EXISTS((user)-[:ENROLLED_IN]->(course))")
    Boolean findEnrollmentStatus(String username,String identifier);

    @Query("MATCH (user:User),(course:Course) WHERE user.username = $username and course.identifier = $identifier" +
    " CREATE (user) -[:ENROLLED_IN] -> (course) RETURN user,course ")
    CourseEnrollmentQueryResult createEnrollmentRelationship(String username,String identifier);

}
