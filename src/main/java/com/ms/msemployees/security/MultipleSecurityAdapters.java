package com.ms.msemployees.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ms.msemployees.security.jwt.JwtAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled=true
)
public class MultipleSecurityAdapters{

	@Configuration
	@Order(1)
	public static class BasicInMemorySecurityAdapter extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private JwtAuthenticationFilter jwtAuthenticationFilter;

		@Autowired
		private PasswordEncoder passwordEncoder;
		
		@Autowired
		private UserDetailsService userDetailsService;
		
		
		@Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			// TODO Auto-generated method stub
			
			/*auth.inMemoryAuthentication()
			    .withUser("user").password("$2a$10$ak3CkbSe.NI2kutm6ASE9Oih.uNynWVuXSLHGL509wk.T.3hGsWqi").authorities("ROLE_USER")
			    .and()
			    .withUser("admin").password("$2a$10$ak3CkbSe.NI2kutm6ASE9Oih.uNynWVuXSLHGL509wk.T.3hGsWqi").authorities("ROLE_MANAGER","ROLE_ADMIN");
			*/
			
			auth.userDetailsService(userDetailsService)
			    .passwordEncoder(passwordEncoder);
			
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// TODO Auto-generated method stub
			http
			    .csrf().disable()
			    .authorizeRequests()
			    //.antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
			    .antMatchers("/auth/**").permitAll()
			    .antMatchers("/api/**").authenticated()
			    .and()
			    .exceptionHandling().authenticationEntryPoint((req,res,ex)->res.sendError(HttpServletResponse.SC_UNAUTHORIZED,"EntryPoint : "+ex.getMessage()))
			    .and()
			    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			    .and()
			    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			    //.httpBasic();
		}
		
		@Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/v2/api-docs",
	                                   "/configuration/ui",
	                                   "/swagger-resources/**",
	                                   "/configuration/security",
	                                   "/swagger-ui.html",
	                                   "/webjars/**");
	    }
	
	
	}

}
