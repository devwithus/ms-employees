package com.ms.msemployees.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public PasswordEncoder() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		System.out.println(passwordEncoder.encode("adam@2020@"));
		System.out.println(passwordEncoder.encode("admin@2020@"));

	}

}
