package com.example.userservice.services;




import com.example.userservice.dtos.SendEmailMessageDto;
import com.example.userservice.dtos.UserDto;
import com.example.userservice.exceptions.UserAlreadyExistsException;
import com.example.userservice.exceptions.UserDoesNotExistException;
import com.example.userservice.models.Session;
import com.example.userservice.models.SessionStatus;
import com.example.userservice.models.User;
import com.example.userservice.repositories.SessionRepository;
import com.example.userservice.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

import com.example.userservice.client.KafkaProducerClient;

@Service
public class AuthService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private KafkaProducerClient kafkaProducerClient;
    private ObjectMapper objectMapper;
//    private BCryptPasswordEncoder bCryptPasswordEncodersswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, PasswordEncoder passwordEncoder, KafkaProducerClient kafkaProducerClient,
                       ObjectMapper objectMapper) {//, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
        this.kafkaProducerClient = kafkaProducerClient;
        this.objectMapper = objectMapper;
//        this.passwordEncoder = new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return null;
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return false;
//            }
        //this.passwordEncoder = new BCryptPasswordEncoder(); // Use BCryptPasswordEncoder

    };



    public ResponseEntity<UserDto> login(String email, String password) throws UserDoesNotExistException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UserDoesNotExistException("User with email: " + email + " doesn't exist.");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        RandomStringUtils randomStringUtils = new RandomStringUtils();
        // TODO: Update here to use Jwt
        // Payload:
        // {
        //    userId:
        //    email:
        //    roles: [
        //    ]
        // }
        // Map<String, Object> claimsMap;
        // claimsMap.add(userId, 123);


        String token = RandomStringUtils.randomAscii(20);
        MultiValueMapAdapter<String, String > headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add("AUTH_TOKEN", token);

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);


        UserDto userDto = UserDto.from(user);
        ResponseEntity<UserDto> response = new ResponseEntity<>(
                userDto,
                headers,
                HttpStatus.OK
        );

        return response;
    }

    public ResponseEntity<Void> logout(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty()) {
            return null;
        }

        Session session = sessionOptional.get();

        session.setSessionStatus(SessionStatus.LOGGED_OUT);

        sessionRepository.save(session);

        return ResponseEntity.ok().build();
    }

    public UserDto signUp(String email, String password) throws UserAlreadyExistsException {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (!userOptional.isEmpty()) {
            throw new UserAlreadyExistsException("User with " + email + " already exists.");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        
        User savedUser = userRepository.save(user);

        UserDto userDto = UserDto.from(savedUser);


        try {
            kafkaProducerClient.sendMessage("userSignUp", objectMapper.writeValueAsString(userDto));

            SendEmailMessageDto emailMessage = new SendEmailMessageDto();
            emailMessage.setTo(userDto.getEmail());
            emailMessage.setFrom("admin@google.com");
            emailMessage.setSubject("Welcome");
            emailMessage.setBody("Thanks for creating an account. We look forward to you growing");
            kafkaProducerClient.sendMessage("sendEmail", objectMapper.writeValueAsString(emailMessage));
        } catch (Exception e) {
            System.out.println("Something has gone wrong");
        }

        return userDto;
    }

    public Optional<UserDto> validate(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty()) {
            return Optional.empty();
        }

        Session session = sessionOptional.get();

        if (!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
            return Optional.empty();
        }

        User user = userRepository.findById(userId).get();

        UserDto userDto = UserDto.from(user);

//        if (!session.getExpiringAt() > new Date()) {
//            return SessionStatus.EXPIRED;
//        }

        return Optional.of(userDto);
    }

}

/// GET /products/1 -> abcd1234abcd
//        /authentication/validate/abcd1234abcd>u_id=12 -> true
// if the token is any random strong, we will first need to make
// a db call to validate the token
// and then another call to get the details of the user
// auth-token%3AeyJjdHkiOiJ0ZXh0L3BsYWluIiwiYWxnIjoiSFMyNTYifQ.ewogICAiZW1haWwiOiAibmFtYW5Ac2NhbGVyLmNvbSIsCiAgICJyb2xlcyI6IFsKICAgICAgIm1lbnRvciIsCiAgICAgICJ0YSIKICAgXSwKICAgImV4cGlyYXRpb25EYXRlIjogIjIzcmRPY3RvYmVyMjAyMyIKfQ.r2FVQUCn6DNHir5AlEBT2XQMgO7aN4m3xg9zcuB-zxQ
// auth-token%3AeyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE2OTgwNzgzNDg0NTQsInJvbGVzIjpbXSwiZXhwaXJ5QXQiOjE5NjU2LCJlbWFpbCI6Im5hbWFuQHNjYWxlci5jb20ifQ._v1af8cc1YA-cEyHlX1BASwveBiASQeteWFM8UzWxfY