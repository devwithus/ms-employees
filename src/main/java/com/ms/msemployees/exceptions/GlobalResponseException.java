package com.ms.msemployees.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ms.msemployees.dtos.ResponseError;
import com.ms.msemployees.mappers.ResponseMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice
public class GlobalResponseException extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> details = new ArrayList<String>();
		details = ex.getBindingResult()
				    .getFieldErrors()
					.stream()
					.map(error -> error.getObjectName()+ " : " +error.getDefaultMessage())
					.collect(Collectors.toList());
		
		ResponseError err = new ResponseError(LocalDateTime.now(), "Validation errors" ,details);
		
		return ResponseMapper.errorToEntity(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
       
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		log.info("ResourceNotFoundException : "+ex.getMessage());
		ResponseError err = new ResponseError(LocalDateTime.now(), "Bad Request!" ,details);
		
		return ResponseMapper.errorToEntity(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
	/*@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ResponseError err = new ResponseError(LocalDateTime.now(), "Internal Server Error" ,details);
		
		return ResponseMapper.errorToEntity(err,HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	
}
