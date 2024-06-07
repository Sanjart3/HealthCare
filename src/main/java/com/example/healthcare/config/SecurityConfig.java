package com.example.healthcare.config;

import com.example.healthcare.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/patient/sign-up", "/patient/login", "/login").permitAll()
//                                .anyRequest().authenticated()
                                .anyRequest().permitAll()
                );
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login") // Custom login page URL
//                        .loginProcessingUrl("/patient/login")  // Endpoint to handle login form POST
//                        .usernameParameter("username") // Specify the username parameter name used in the form
//                        .passwordParameter("password") // Specify the password parameter name used in the form
//                        .defaultSuccessUrl("/patient/patient2.html", true)  // Redirect to patient.html on successful login
//                        .permitAll()
//                )
//                .logout(logout ->
//                        logout
//                                .permitAll()
//                );

        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
