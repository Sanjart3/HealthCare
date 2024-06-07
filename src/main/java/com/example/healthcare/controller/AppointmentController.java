package com.example.healthcare.controller;

import com.example.healthcare.dto.AppoitmentDto;
import com.example.healthcare.entity.DoctorSchedule;
import com.example.healthcare.service.AppointmentService;
import com.example.healthcare.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DoctorScheduleService scheduleService;

    @GetMapping("/schedule")
    public ResponseEntity getScheduleByDay(@RequestParam LocalDate dateInput){
        List<DoctorSchedule> schedule = scheduleService.getByDay(dateInput);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/data")
    public ResponseEntity getFullScheduleByDay(@RequestParam LocalDate dateInput){
        List<AppoitmentDto> schedule = appointmentService.getFullByDay(dateInput);
        return ResponseEntity.ok(schedule);
    }
}
