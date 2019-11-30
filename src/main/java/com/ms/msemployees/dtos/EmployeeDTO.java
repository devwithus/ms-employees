package com.ms.msemployees.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {

	 @NotEmpty
	 private String name;
	 @NotEmpty
	 @Email
	 private String email;

}
