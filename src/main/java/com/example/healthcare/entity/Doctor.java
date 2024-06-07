package com.example.healthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_doctor")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "doctor_address")
    private String address;

    @Column(name = "doctor_phone")
    private String phone;

    @Column(name = "doctor_email")
    private String email;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "booking_price")
    private Long bookingPrice;
}
