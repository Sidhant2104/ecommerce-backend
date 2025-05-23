package com.projects.EcommerceApp.service;

import com.projects.EcommerceApp.model.User;
import com.projects.EcommerceApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // * CREATE or SAVE/REGISTER a user
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // * LOGIN using email and password:
    @Override
    public User loginUser(String email, String password) {
        // Check is user exists or not
        User user = userRepository.findByEmail(email);
        // if the user exixts and  is not empty then check weather the password matches the password stored in the database.
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    //* GET ALL users saved in the database:
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // * GET Users by ID:
    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // * Delete User by ID:
    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }


}
