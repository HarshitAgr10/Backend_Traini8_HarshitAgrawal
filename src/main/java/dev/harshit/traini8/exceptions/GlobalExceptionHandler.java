package dev.harshit.traini8.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Global exception handler that catches exceptions thrown across the application
     * and translates them into appropriate HTTP responses with error messages.
     */

    /**
     * Handles validation exceptions thrown when method arguments fail validation.
     *
     * @return a ResponseEntity containing an ExceptionTranslator with error details and a 400 status code
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionTranslator> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ExceptionTranslator exceptionTranslator = new ExceptionTranslator();
        exceptionTranslator.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        exceptionTranslator.setStatusCode(ex.getStatusCode().value());
        return new ResponseEntity<>(exceptionTranslator, HttpStatusCode.valueOf(400));
    }


    /**
     * Handles exceptions related to duplicate training center registrations.
     *
     * @return a ResponseEntity containing an ExceptionTranslator with error details and a 500 status code
     */
    @ExceptionHandler(DuplicateTrainingCenterException.class)
    public ResponseEntity<ExceptionTranslator> handleDuplicateTrainingCenterException(DuplicateTrainingCenterException ex) {
        // Return the error message as the response body
        ExceptionTranslator exceptionTranslator = new ExceptionTranslator();
        exceptionTranslator.setMessage(ex.getMessage());
        exceptionTranslator.setStatusCode(500);
        return new ResponseEntity<>(exceptionTranslator, HttpStatusCode.valueOf(500));
    }
}
