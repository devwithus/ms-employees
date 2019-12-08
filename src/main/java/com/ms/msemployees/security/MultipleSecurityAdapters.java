package com.ms.msemployees.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled=true
)
public class MultipleSecurityAdapters{

	@Configuration
	@Order(1)
	public static class BasicInMemorySecurityAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private PasswordEncoder passwordEncoder;
		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			// TODO Auto-generated method stub
			
			auth.inMemoryAuthentication()
			    .withUser("adam").password(passwordEncoder.encode("adam@2020@")).roles("USER")
			    .and()
			    .withUser("admin").password(passwordEncoder.encode("admin@2020@")).roles("USER","ADMIN");
			
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// TODO Auto-generated method stub
			http
			    .csrf().disable()
			    .authorizeRequests()
			    .antMatchers("/api/**").authenticated()
			    .and()
			    .exceptionHandling().authenticationEntryPoint((req,res,ex)->res.sendError(HttpServletResponse.SC_UNAUTHORIZED,ex.getMessage()))
			    .and()
			    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			    .and()
			    .httpBasic();
		}
	
	}

}
