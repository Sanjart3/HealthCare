package com.example.healthcare.service;

import com.example.healthcare.dto.AppoitmentDto;
import com.example.healthcare.entity.DoctorSchedule;
import com.example.healthcare.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<AppoitmentDto> getFullByDay(LocalDate dateInput) {
        List<Object[]> result = appointmentRepository.findSchedulesByDate(dateInput);
        return result.stream().map(this::mapToAppoitmentDto).collect(Collectors.toList());
    }

    private AppoitmentDto mapToAppoitmentDto(Object[] result) {
        return new AppoitmentDto(
                ((Number) result[0]).longValue(),
                (String) result[5],
                (String) result[6],
                ((java.sql.Date) result[3]).toLocalDate(),
                (String) result[2],
                (String) result[3],
                ((Number) result[7]).longValue(),
                (Boolean) result[4]
        );
    }
}
