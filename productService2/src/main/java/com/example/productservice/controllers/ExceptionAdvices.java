package com.example.productservice.controllers;


//import org.junit.jupiter.api.Test;
import com.example.productservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> viswa(Exception exception) {
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
//        errorResponseDto.setErrorMessage(exception.getMessage());
//
//        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("code broke", HttpStatus.OK);
    }


//    @Test
//    void testingIfAnandIsAskingCorrectQuestion() {
//        assert false;
//    }
}
