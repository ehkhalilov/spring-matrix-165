package org.example.matrixspringapp165.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.example.matrixspringapp165.exception.NotFoundException;
import org.example.matrixspringapp165.model.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handler(Exception exception) {
        exception.printStackTrace();
        log.error("ActioLog.handler.error message {}", exception.getMessage());
        return new ExceptionDto("UNEXPECTED_ERROR");
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handler(NotFoundException exception) {
        log.error("ActioLog.not-found-handler.error message {}", exception.getMessage());
        return new ExceptionDto(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionDto> handler(MethodArgumentNotValidException exception) {
        log.error("ActioLog.bad-request-handler.error message {}", exception.getMessage());
        return exception.getAllErrors()
                .stream()
                .map(e -> new ExceptionDto(e.getDefaultMessage())).toList();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handler(ConstraintViolationException exception) {
        log.error("ActioLog.bad-request-violation-handler.error message {}", exception.getMessage());
        return new ExceptionDto(exception.getMessage());
    }
}
