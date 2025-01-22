package com.tanishqsingh.curd_sql.controller;

import com.tanishqsingh.curd_sql.entity.User;
import com.tanishqsingh.curd_sql.repository.UserRepository;
import com.tanishqsingh.curd_sql.service.UserService;
import com.tanishqsingh.curd_sql.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired UserService userService;
    @Autowired private JwtUtils jwtUtils;
    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> allUsers() {
        return userService.allUsers();
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Optional<User> dbUser = userRepository.findByUsername(user.getUsername());

        return dbUser.map(value -> passwordEncoder.matches(user.getPassword(), value.getPassword()) ? jwtUtils.generateJwtToken(value) : null).orElse(null);
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        Optional<User> userRegistered = userService.addUser(user);
        return userRegistered.isPresent() ? ResponseEntity.ok(userRegistered) : ResponseEntity.ok("User already registered.");
    }
}
