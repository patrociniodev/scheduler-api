package br.com.isaacpatrocinio.scheduler.scheduler_api.resources.exceptions;

import br.com.isaacpatrocinio.scheduler.scheduler_api.services.exceptions.ResourceNotFoundException;
import br.com.isaacpatrocinio.scheduler.scheduler_api.services.exceptions.ScheduleException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError errorObj = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Resource not found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorObj);
    }

    @ExceptionHandler(ScheduleException.class)
    public ResponseEntity<StandardError> scheduleError(ScheduleException e, HttpServletRequest request) {
        StandardError errorObj = new StandardError(Instant.now(), HttpStatus.CONFLICT.value(), "Schedule error", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorObj);
    }
}
