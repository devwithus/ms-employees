package com.ms.msemployees.dtos;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {
	
	 @NotEmpty
	 private String name;
	 @NotEmpty
	 private String email;

}
