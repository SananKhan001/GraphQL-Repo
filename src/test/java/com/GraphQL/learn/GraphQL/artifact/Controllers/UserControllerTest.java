package com.GraphQL.learn.GraphQL.artifact.Controllers;

import com.GraphQL.learn.GraphQL.artifact.Entities.User;
import com.GraphQL.learn.GraphQL.artifact.Repository.UserRepo;
import com.GraphQL.learn.GraphQL.artifact.Request.UserInput;
import com.GraphQL.learn.GraphQL.artifact.Service.UserService;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@GraphQlTest(UserController.class)
@Import(UserService.class)
class UserControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    private JSONObject response;

    @MockBean
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        response = new JSONObject();
    }

    @Test
    @RepeatedTest(3)
    void createUser() throws JSONException {
        User userInput = new User(UUID.randomUUID().toString(),
                             UUID.randomUUID() + "@gmail.com",
                                    UUID.randomUUID().toString(),
                                    UUID.randomUUID().toString());

        User userExpected = User.builder()
                .userId(1)
                .name(userInput.getName())
                .email(userInput.getEmail())
                .password(userInput.getPassword())
                .phone(userInput.getPhone()).build();

        Mockito.when(userRepo.save(userInput)).thenReturn(userExpected);

        // language=GraphQL
        String document = """
        mutation createUser($name: String!, $email: String!, $password: String!, $phone: String!) {
           createUser(
             name: $name
             email: $email
             password: $password
             phone: $phone
           ) {
             name
             email
             userId
           }
         }
        """;

        response.put("name", userInput.getName());
        response.put("email", userInput.getEmail());
        response.put("userId", String.valueOf(userExpected.getUserId()));

        String expectedResponse = response.toString();

        graphQlTester.document(document)
                .variable("name", userInput.getName())
                .variable("email", userInput.getEmail())
                .variable("password", userInput.getPassword())
                .variable("phone", userInput.getPhone())
                .execute()
                .path("createUser")
                .matchesJson(
                        expectedResponse
                );
    }

    // TODO:: testCreateUser
//    @Test
//    void testCreateUser() {
//    }
//
    // TODO:: getAllUser
//    @Test
//    void getAllUser() {
//    }
//
    // TODO:: getUser
//    @Test
//    void getUser() {
//    }
//
    // TODO:: deleteUser
//    @Test
//    void deleteUser() {
//    }

}