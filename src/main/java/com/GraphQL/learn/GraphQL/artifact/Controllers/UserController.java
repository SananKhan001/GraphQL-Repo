package com.GraphQL.learn.GraphQL.artifact.Controllers;

import com.GraphQL.learn.GraphQL.artifact.Entities.User;
import com.GraphQL.learn.GraphQL.artifact.Request.UserInput;
import com.GraphQL.learn.GraphQL.artifact.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @MutationMapping // We can also specify name to map @MutationMapping(name = "name_specified_in_schema")
    public User createUser(@Argument String name,
                           @Argument String email,
                           @Argument String password,
                           @Argument String phone){
        User user = User.builder()
                .email(email)
                .name(name)
                .phone(phone)
                .password(password)
                .build();
        return userService.createUser(user);
    }

    @MutationMapping(name = "createUserByUserInput")
    public User createUser(@Argument UserInput userInput){
        User user = User.builder()
                .password(userInput.getPassword())
                .phone(userInput.getPhone())
                .name(userInput.getName())
                .email(userInput.getEmail())
                .build();
        return userService.createUser(user);
    }

    @QueryMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @QueryMapping
    public User getUser(@Argument int userId){
        return userService.getUser(userId);
    }

    @MutationMapping
    public boolean deleteUser(@Argument int userId){
        return userService.deleteUser(userId);
    }

}
