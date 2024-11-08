package com.sweeti.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sweeti.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExeptionHandler {
	
	 @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse res = new ApiResponse();
		res.setMessage(message);
		res.setSuccess(true);
		res.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    	 
     }
}
