package com.GraphQL.learn.GraphQL.artifact.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {
    private String name;
    private String email;
    private String password;
    private String phone;
}
