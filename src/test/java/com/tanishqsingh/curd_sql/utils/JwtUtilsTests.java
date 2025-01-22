package com.tanishqsingh.curd_sql.utils;

import com.tanishqsingh.curd_sql.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilsTests {
    @Autowired
    private JwtUtils jwtUtils;

//    @Test
//    public void generateToken() {
//        User user = new User();
//        user.setUsername("tanishqsingh");
//        user.setPassword("123456");
//
//        System.out.println(jwtUtils.generateJwtToken(user));
//    }
}
