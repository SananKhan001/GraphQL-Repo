package com.GraphQL.learn.GraphQL.artifact.Request;

import lombok.Data;

@Data
public class UserInput {
    private String name;
    private String email;
    private String password;
    private String phone;
}
