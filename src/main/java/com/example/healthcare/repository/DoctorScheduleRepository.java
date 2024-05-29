package com.example.healthcare.repository;

import com.example.healthcare.entity.Doctor;
import com.example.healthcare.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {

    List<DoctorSchedule> findAllByDoctor(Doctor doctor);
}
