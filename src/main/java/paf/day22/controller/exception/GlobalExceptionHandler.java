package paf.day22.controller.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import paf.day22.model.exception.ApiError;
import paf.day22.model.exception.ResourceNotFoundException;

// Global handler for exceptions occurred (listed here) while running app
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request, HttpServletResponse response) {
        ApiError apiError = new ApiError(404, ex.getMessage(), new Date(), request.getRequestURI());

        return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }
}
