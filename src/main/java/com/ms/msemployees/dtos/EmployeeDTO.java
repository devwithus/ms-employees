package com.ms.msemployees.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.ms.msemployees.cannotations.UniqName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {

	 @UniqName
	 @NotEmpty(message = "{name.empty}")
	 private String name;
	 @NotEmpty(message = "{email.empty}")
	 @Email(message = "{email.format}")
	 private String email;

}
