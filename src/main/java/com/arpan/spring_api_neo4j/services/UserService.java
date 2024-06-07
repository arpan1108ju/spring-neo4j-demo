package com.arpan.spring_api_neo4j.services;

import com.arpan.spring_api_neo4j.models.User;
import com.arpan.spring_api_neo4j.repositories.UserRepository;
import com.arpan.spring_api_neo4j.requests.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequest request){

        // TODO make suer to check the username doesn't exist in the db

          User user = new User();
          user.setName(request.getName());
          user.setUsername(request.getUsername());
          user.setRoles(request.getRoles());
          user.setPassword(passwordEncoder.encode(request.getPassword()));

          userRepository.save(user);

          return user;
    }

}
