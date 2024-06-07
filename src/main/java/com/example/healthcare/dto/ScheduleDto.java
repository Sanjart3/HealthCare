package com.example.healthcare.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleDto {
    private LocalDate scheduleDay;
    private String startTime;
    private String endTime;
    private Boolean isAvailable;
}
