package com.GraphQL.learn.GraphQL.artifact.Repository;

import com.GraphQL.learn.GraphQL.artifact.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
