package com.example.healthcare.controller;

import com.example.healthcare.dto.AuthDTO;
import com.example.healthcare.dto.SignUpRequest;
import com.example.healthcare.entity.Patient;
import com.example.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, Long>> signUp(@RequestBody SignUpRequest patient) {
        Patient savedPatient = patientService.createPatient(patient);
        Map<String, Long> response = new HashMap<>();
        response.put("id", savedPatient.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}/update")
    public ResponseEntity updatePatient(@PathVariable("id") Long id,
                                        @RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatePatient(id,patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @GetMapping("/me")
    public ResponseEntity<Patient> getCurrentPatient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        Patient patient = patientService.findByEmail(email);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO authDTO){
        return ResponseEntity.ok(patientService.authentication(authDTO));
    }
}
