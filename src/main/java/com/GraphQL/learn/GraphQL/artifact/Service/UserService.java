package com.GraphQL.learn.GraphQL.artifact.Service;

import com.GraphQL.learn.GraphQL.artifact.Entities.User;
import com.GraphQL.learn.GraphQL.artifact.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public User getUser(int userId){
        return userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Specified User is not present in DB !!!"));
    }

    public boolean deleteUser(int userId){
        userRepo.deleteById(userId);
        return true;
    }

    // TODO:: Update user

}
