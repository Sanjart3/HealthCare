package com.example.healthcare.controller;

import com.example.healthcare.entity.DoctorSchedule;
import com.example.healthcare.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("{id}/schedule")
public class ScheduleController {

    @Autowired
    private DoctorScheduleService scheduleService;
    @Autowired
    private DoctorScheduleService doctorScheduleService;

    @PostMapping("add-schedule")
    public ResponseEntity addSchedule(@PathVariable("id") Long id,
                                      @RequestBody DoctorSchedule doctorSchedule){
        DoctorSchedule schedule = scheduleService.addSchedule(id, doctorSchedule);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("get-all")
    public ResponseEntity getAllSchedule(@PathVariable("id") Long id){
        List<DoctorSchedule> schedules = scheduleService.getAllSchedules(id);
        return ResponseEntity.ok(schedules);
    }

    @PutMapping("{schedule-id}/update")
    public ResponseEntity updateSchedule(@PathVariable("id") Long id,
                                         @PathVariable("schedule-id") Long schedule_id,
                                         @RequestBody DoctorSchedule doctorSchedule){
        DoctorSchedule updatedSchedule = doctorScheduleService.updateSchedule(schedule_id, doctorSchedule);
        return ResponseEntity.ok(updatedSchedule);
    }
}
