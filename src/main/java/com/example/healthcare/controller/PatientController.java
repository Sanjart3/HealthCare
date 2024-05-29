package com.example.healthcare.controller;

import com.example.healthcare.entity.Patient;
import com.example.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity getPatientById(@PathVariable("id") Long id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/sign-up")
    public ResponseEntity registerPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.createPatient(patient);
        return ResponseEntity.ok(savedPatient);
    }

    @PutMapping("{id}/update")
    public ResponseEntity updatePatient(@PathVariable("id") Long id,
                                        @RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatePatient(id,patient);
        return ResponseEntity.ok(updatedPatient);
    }
}
