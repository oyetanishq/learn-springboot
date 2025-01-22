package com.tanishqsingh.curd_sql.service;

import com.tanishqsingh.curd_sql.entity.Role;
import com.tanishqsingh.curd_sql.entity.User;
import com.tanishqsingh.curd_sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired private UserRepository userRepository;
    @Autowired private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public Optional<User> addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);

        return userRepository.findByUsername(user.getUsername()).isPresent() ? Optional.empty() : Optional.of(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
