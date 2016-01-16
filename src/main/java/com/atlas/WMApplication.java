package com.atlas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@SpringBootApplication
public class WMApplication  {

	public static void main(String[] args) {
		SpringApplication.run(WMApplication.class, args);
	}

	@EnableWebSecurity
	static class SecConfig extends WebSecurityConfigurerAdapter{

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		}

	}
}
