package com.example.productservice;

import com.example.productservice.inheritanceexamples.mappedsuperclass.MSMentorRepository;
//import com.example.productservice.inheritanceexamples.mappedsuperclass.MSUserRepository;
import com.example.productservice.inheritanceexamples.mappedsuperclass.Mentor;
import com.example.productservice.inheritanceexamples.mappedsuperclass.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductService2ApplicationTests {

    @Test
    void contextLoads() {
    }

    //@Autowired
//    private MSUserRepository userRepository;
    @Autowired
    private MSMentorRepository mentorRepository;



//    @Test
//    void testDifferentInheritances() {
////        User user = new User();
////        user.setEmail("naman@scaler.com");
////        user.setPassword("password");
////        userRepository.save(user);
//
//        Mentor mentor = new Mentor();
//        mentor.setEmail("nmn@sclr.com");
//        mentor.setPassword("psswrd");
//        mentor.setNumberOfMentees(4);
//        mentor.setNumberOfSessions(50);
//        mentorRepository.save(mentor);
//    }

}
