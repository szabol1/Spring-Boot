package com.example.demo.security;

import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // Define UserDetailsService based on UserService
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            var user = userService.findUserByUsername(username);
            if (user != null) {
                return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    Collections.emptyList()  // No authorities/roles for now
                );
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        };
    }

    // Define authentication manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //for when form is connected
   /*  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for APIs (but could enable for login forms)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/register", "/api/login").permitAll()  // Ensure login/register pages are public
                .anyRequest().authenticated()  // All other endpoints require authentication
            )
            .formLogin(form -> form
                .loginPage("/api/login")  // Specify login page endpoint
                .permitAll()  // Allow everyone to access the login page
                .defaultSuccessUrl("/home", true)  // Redirect to /home after successful login
                .failureUrl("/api/login?error=true")  // Redirect to /login on authentication failure
            )
            .logout(logout -> logout.permitAll());
    
        return http.build();
    }
    */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for APIs (not needed for stateless APIs)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/register", "/api/login").permitAll() 
                .anyRequest().authenticated()  // Require authentication for other endpoints
            )
            .httpBasic(Customizer.withDefaults());  
    
        return http.build();
    }
}

