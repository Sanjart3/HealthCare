package com.example.healthcare.controller;

import com.example.healthcare.dto.ScheduleDto;
import com.example.healthcare.entity.DoctorSchedule;
import com.example.healthcare.entity.Patient;
import com.example.healthcare.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("{id}/schedule")
public class ScheduleController {

    @Autowired
    private DoctorScheduleService scheduleService;

    @PostMapping("add-schedule")
    public ResponseEntity addSchedule(@PathVariable("id") Long id,
                                      @RequestBody ScheduleDto scheduleDto){
        DoctorSchedule schedule = scheduleService.addSchedule(id, scheduleDto);
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
        DoctorSchedule updatedSchedule = scheduleService.updateSchedule(schedule_id, doctorSchedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    @GetMapping("get/by-day")
    public ResponseEntity getScheduleByDay(@RequestParam LocalDate dateInput){
        List<DoctorSchedule> schedule = scheduleService.getByDay(dateInput);
        return ResponseEntity.ok(schedule);
    }

    @DeleteMapping("delete/{schedule-id}")
    public ResponseEntity deleteSchedule(@PathVariable("id") Long id,
                                         @PathVariable("schedule-id") Long scheduleId){
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok("Deleted Successfully");
    }

}
