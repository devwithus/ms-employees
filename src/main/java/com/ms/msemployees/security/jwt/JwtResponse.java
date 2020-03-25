package com.ms.msemployees.security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {
	
	private String accessToken;
    private String tokenType = "Bearer";
    
    public JwtResponse (String accessToken) {
        this.accessToken = accessToken;
    }

}
