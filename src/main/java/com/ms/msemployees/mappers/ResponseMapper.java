package com.ms.msemployees.mappers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ms.msemployees.dtos.ResponseError;

public class ResponseMapper {

	public static ResponseEntity<Object> errorToEntity(ResponseError err,HttpStatus status) {
		return new ResponseEntity<Object>(err,status);
	}
}
