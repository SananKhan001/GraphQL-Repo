package com.GraphQL.learn.GraphQL.artifact.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user_details")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String name;
    private String email;
    private String password;
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Order> orderList;

    public User(String name, String email, String pass, String phone) {
        this.name = name;
        this.email = email;
        this.password = pass;
        this.phone = phone;
    }

    public User(int userId, String name, String email, String pass, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = pass;
        this.phone = phone;
    }
}
