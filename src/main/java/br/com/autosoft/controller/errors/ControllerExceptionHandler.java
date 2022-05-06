package br.com.autosoft.controller.errors;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.autosoft.exceptions.EntityNotFoundException;
import br.com.autosoft.exceptions.NoSuchElementException;
import br.com.autosoft.exceptions.RegisteredEntityException;
import br.com.autosoft.exceptions.DataIntegrityViolationException;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException entityNotFound, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(entityNotFound.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> noSuchElement(NoSuchElementException noSuchElement, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("No Such Element");
        err.setMessage(noSuchElement.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(RegisteredEntityException.class)
    public ResponseEntity<StandardError> registeredEntity(RegisteredEntityException registeredEntity, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setError("Entity Registered");
        err.setMessage(registeredEntity.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolationException dataIntegrityViolation, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setError("Entity Conflict");
        err.setMessage(dataIntegrityViolation.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
