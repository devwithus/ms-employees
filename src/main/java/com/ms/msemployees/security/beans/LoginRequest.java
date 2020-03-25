package com.ms.msemployees.security.beans;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
public class LoginRequest {
	
	@NotBlank
    private String username;

    @NotBlank
    private String password;

}
