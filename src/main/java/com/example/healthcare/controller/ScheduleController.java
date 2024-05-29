package com.example.healthcare.controller;

import com.example.healthcare.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private DoctorScheduleService scheduleService;
}
