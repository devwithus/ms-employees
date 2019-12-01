package com.ms.msemployees.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ms.msemployees.dtos.ResponseError;
import com.ms.msemployees.mappers.ResponseMapper;


@ControllerAdvice
public class GlobalResponseException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
       
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ResponseError err = new ResponseError(LocalDateTime.now(), "NOT Found!" ,details);
		
		return ResponseMapper.errorToEntity(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		
		List<String> details = new ArrayList<String>();
		details.add(request.getDescription(false));
		ResponseError err = new ResponseError(LocalDateTime.now(), ex.getMessage(),details);
		
		return ResponseMapper.errorToEntity(err,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
