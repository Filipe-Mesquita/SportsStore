package com.example.demo.configs;
import com.example.demo.classes.UserObj;

import java.util.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/login","/h2-console").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll())
			.sessionManagement(session -> session
		        .sessionFixation().newSession()  // Creates a new session upon login
		        .maximumSessions(1) // Restricts user to one session at a time
		        .expiredUrl("/login?sessionExpired") // Redirects if session expires
		    );

		return http.build();
	}
	public ArrayList<UserObj> userList = new ArrayList<>(Arrays.asList(new UserObj("user","password"),new UserObj("admin","password")));
	@Bean
	public UserDetailsService userDetailsService() {
	    List<UserDetails> userDetailsList = new ArrayList<>();

	    for (UserObj userObj : userList) {
	        UserDetails userDetails = User.withUsername(userObj.getName())
	                .password("{noop}" + userObj.getPassword()) // {noop} means no password encoding for testing
	                .roles("USER") // Default role; modify as needed
	                .build();

	        userDetailsList.add(userDetails);
	    }

	    return new InMemoryUserDetailsManager(userDetailsList);
	}
}
