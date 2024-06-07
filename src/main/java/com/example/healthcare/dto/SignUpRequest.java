package com.example.healthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private LocalDate dateOfBirth;
}