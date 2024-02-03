package br.com.daniloewerton.apiv1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceResource {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DefaultException> getEntityExceptionHandler(EntityNotFoundException exception) {
        int statusCode = HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(statusCode)
                .body(new DefaultException(statusCode, exception.getMessage()));
    }
}