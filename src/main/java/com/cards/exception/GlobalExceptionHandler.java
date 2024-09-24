package com.cards.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cards.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	
	 @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
	            WebRequest request) {
	        Map<String, String> validationErrors = new HashMap<>();
	        java.util.List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

	        validationErrorList.forEach(error -> {
	            String fieldName = ((FieldError) error).getField();
	            String validationMsg = error.getDefaultMessage();
	            validationErrors.put(fieldName, validationMsg);
	        });
	        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception,WebRequest webRequest){
		 ErrorResponse errorResponse = 
				 new ErrorResponse(webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, 
						 exception.getMessage(), LocalDateTime.now());
		 
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				 .body(errorResponse);
	 }
	 
	 @ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception,
			 WebRequest webRequest){
		 
		 ErrorResponse errorResponse =
				 new ErrorResponse(webRequest.getDescription(false), HttpStatus.NOT_FOUND,
						 exception.getMessage(), LocalDateTime.now());
		 
		 return ResponseEntity.status(HttpStatus.NOT_FOUND)
				 .body(errorResponse);
	 }
	 
	 @ExceptionHandler(CardAlreadyExistsException.class)
	 public ResponseEntity<ErrorResponse> handleCardAlreadyException(CardAlreadyExistsException exception,
			 WebRequest webRequest){
		 
		 ErrorResponse errorResponse =
				 new ErrorResponse(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
						 exception.getMessage(), LocalDateTime.now());
		 
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				 .body(errorResponse);
	 }
	 
	 
}
