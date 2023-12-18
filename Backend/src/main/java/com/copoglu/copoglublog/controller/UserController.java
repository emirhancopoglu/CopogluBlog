package com.copoglu.copoglublog.controller;

import com.copoglu.copoglublog.entities.User;
import com.copoglu.copoglublog.request.LoginRequest;
import com.copoglu.copoglublog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginRequest loginRequest;

    @GetMapping("GetAll")
    public List<User> GetAllUser(){
        return userService.GetAllUsers();
    }

    @PostMapping("/add")
    public User AddUser(@RequestBody User user){
        return userService.AddUser(user);
    }

    @GetMapping("id/{id}")
    public Optional<User> findbyId (@PathVariable Long id) {
        return userService.findbyId(id);
    }

    @PutMapping("update/{id}")
    public User updateById (@PathVariable Long id , @RequestBody User updatedUser) {
        return userService.updateById(id , updatedUser);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PostMapping("login")
    public User findByUsernameAndPassword(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return userService.findByUsernameAndPassword(username ,password);
    }


    public void updateToken(User user) {
        userService.updateToken(user);
    }




}
