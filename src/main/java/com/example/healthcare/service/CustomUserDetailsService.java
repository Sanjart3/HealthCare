package com.example.healthcare.service;

import com.example.healthcare.entity.Patient;
import com.example.healthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Log the email received
        System.out.println("Email received in CustomUserDetailsService: " + email);

        Optional<Patient> patient = patientRepository.findByEmail(email);
        if (patient.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        // Return the UserDetails object without re-encoding the password
        return User.builder()
                .username(patient.get().getEmail())
                .password(patient.get().getPassword()) // Password should already be encoded in the database
                .roles("USER")
                .build();
    }
}
