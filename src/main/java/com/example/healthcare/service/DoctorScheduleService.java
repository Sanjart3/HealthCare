package com.example.healthcare.service;

import com.example.healthcare.dto.AppoitmentDto;
import com.example.healthcare.dto.ScheduleDto;
import com.example.healthcare.entity.DoctorSchedule;
import com.example.healthcare.entity.Patient;
import com.example.healthcare.exception.ItemNotFoundException;
import com.example.healthcare.repository.DoctorScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoctorScheduleService {

    @Autowired
    private DoctorScheduleRepository scheduleRepository;
    @Autowired
    private DoctorService doctorService;

    public DoctorSchedule addSchedule(Long id, ScheduleDto scheduleDto) {
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        doctorSchedule.setScheduleDay(scheduleDto.getScheduleDay());
        doctorSchedule.setStartTime(scheduleDto.getStartTime());
        doctorSchedule.setEndTime(scheduleDto.getEndTime());
        doctorSchedule.setIsAvailable(scheduleDto.getIsAvailable());
        doctorSchedule.setDoctor(doctorService.getDoctorById(id));
        return scheduleRepository.save(doctorSchedule);
    }

    public List<DoctorSchedule> getAllSchedules(Long id) {
        List<DoctorSchedule> schedules = scheduleRepository.findAllByDoctor(doctorService.getDoctorById(id));
        return schedules;
    }

    public DoctorSchedule updateSchedule(Long id, DoctorSchedule doctorSchedule) {
        DoctorSchedule schedule = scheduleRepository.findById(id).get();
        schedule.setScheduleDay(doctorSchedule.getScheduleDay());
        schedule.setAppointment(doctorSchedule.getAppointment());
        schedule.setStartTime(doctorSchedule.getStartTime());
        schedule.setEndTime(doctorSchedule.getEndTime());
        schedule.setIsAvailable(doctorSchedule.getIsAvailable());
        return scheduleRepository.save(schedule);
    }

    public List<DoctorSchedule> getByDay(LocalDate date){
        List<DoctorSchedule> schedules = scheduleRepository.findAllByScheduleDay(date);
        return schedules;
    }

    public void deleteSchedule(Long scheduleId){
        scheduleRepository.deleteById(scheduleId);
    }

}
