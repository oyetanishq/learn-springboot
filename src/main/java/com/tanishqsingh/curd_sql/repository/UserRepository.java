package com.tanishqsingh.curd_sql.repository;

import com.tanishqsingh.curd_sql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
