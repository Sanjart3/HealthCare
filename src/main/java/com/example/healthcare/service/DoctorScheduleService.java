package com.example.healthcare.service;

import com.example.healthcare.entity.DoctorSchedule;
import com.example.healthcare.repository.DoctorScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorScheduleService {

    @Autowired
    private DoctorScheduleRepository scheduleRepository;
    @Autowired
    private DoctorService doctorService;

    public DoctorSchedule addSchedule(Long id, DoctorSchedule doctorSchedule) {
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
}
