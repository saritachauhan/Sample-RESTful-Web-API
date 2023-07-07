package com.example.rest.webservices.restfulwebservices.Security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
    
		UserDetails user1 = createNewUser("Sarita", "1234");
		UserDetails user2 = createNewUser("Saru", "12345");
    	
        return new InMemoryUserDetailsManager(user1,user2);
    }

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> encoder = input -> passwordEncoder().encode(input);
		UserDetails user = User.builder().passwordEncoder(
    			encoder
    			)
            .username(username)
            .password(password)
            .roles("USER")
            .build();
		return user;
	}
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 

	@Bean
	public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		http.httpBasic(withDefaults());
		http.csrf().disable();
		
		return http.build();
		
	}

}
