package com.projects.EcommerceApp.service;

import com.projects.EcommerceApp.model.User;
import com.projects.EcommerceApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public interface UserService {

    // * CREATE or SAVE/REGISTER a user
    public User registerUser(User user);

    // * LOGIN using email and password:
    public User loginUser(String email, String password);

    // * GET ALL users saved in the database:
    public List<User> getAllUsers();

    // * GET Users by ID:
    public User getUserById(@PathVariable long id);

    // * Delete User by ID:
    public void deleteUserById(@PathVariable long id);
}
