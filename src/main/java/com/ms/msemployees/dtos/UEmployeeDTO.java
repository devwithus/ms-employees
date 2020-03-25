package com.ms.msemployees.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(description = "Employee DTO class For Update")
public class UEmployeeDTO {

	 @ApiModelProperty(notes = "employee DTO NAME")
	 @NotEmpty(message = "{name.empty}")
	 private String name;
	 
	 @ApiModelProperty(notes = "employee DTO EMAIL")
	 @NotEmpty(message = "{email.empty}")
	 @Email(message = "{email.format}")
	 private String email;

}