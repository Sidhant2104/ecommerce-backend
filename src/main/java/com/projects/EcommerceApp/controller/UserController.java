package com.projects.EcommerceApp.controller;

import com.projects.EcommerceApp.dto.LoginDTO;
import com.projects.EcommerceApp.dto.UserRegisterDTO;
import com.projects.EcommerceApp.model.User;
import com.projects.EcommerceApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*") // resource sharing between two domains
public class UserController {

    @Autowired
    private UserService userService;

    // * CREATE or SAVE/REGISTER a user
    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());

        return userService.registerUser(user);
    }


    // * LOGIN using email and password:
    @PostMapping("/login")
    public User loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        return userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());
    }


    //* GET ALL users saved in the database:
    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // * GET User by ID:
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

}
