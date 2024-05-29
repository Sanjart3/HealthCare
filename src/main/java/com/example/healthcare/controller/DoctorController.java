package com.example.healthcare.controller;

import com.example.healthcare.entity.Doctor;
import com.example.healthcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/getDoctor/{id}")
    public ResponseEntity getDoctor(@PathVariable Long id){
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @PutMapping("/{id}/change-profile")
    public ResponseEntity updateDoctor(@PathVariable Long id,
                                        @RequestBody Doctor doctor){
        Doctor savedDoctor = doctorService.updateDoctor(id, doctor);
        return ResponseEntity.ok(doctor);
    }
}
