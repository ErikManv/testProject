package com.hh.testproject.exceptions.handler;

import com.hh.testproject.exceptions.NotFoundException;
import com.hh.testproject.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getFieldErrors()
            .stream().map(FieldError::getDefaultMessage).toList();
        return new ResponseEntity<>(errors.get(0), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleEnumValidationErrors(IllegalArgumentException ex) {
        return new ResponseEntity<>("operation types: WITHDRAW or DEPOSIT",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException m) {
        return new ResponseEntity<>(m.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException m) {
        return new ResponseEntity<>(m.getMessage(),HttpStatus.NOT_FOUND);
    }
}

