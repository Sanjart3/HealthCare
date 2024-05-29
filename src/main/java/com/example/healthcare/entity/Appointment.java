package com.example.healthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_appointment")
    private Long id;

    @Column(name = "symptom")
    private String symptom;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @ManyToMany
    @JoinTable(
            name = "patient_appointment_association",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "id_appointment")
    )
    private Set<Patient> patients;

    @OneToOne
    @JoinColumn(name = "doctor_schedule_id", referencedColumnName = "doctor_schedule_id")
    private DoctorSchedule doctorSchedule;
}
