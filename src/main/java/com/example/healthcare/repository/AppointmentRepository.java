package com.example.healthcare.repository;

import com.example.healthcare.dto.AppoitmentDto;
import com.example.healthcare.entity.Appointment;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "SELECT ds.doctor_schedule_id, ds.schedule_day, ds.start_time, ds.end_time, ds.is_available, " +
            "d.first_name as doctorFirstName, d.last_name as doctorLastName, d.booking_price as bookingPrice " +
            "FROM doctor_schedule ds " +
            "JOIN doctor d on d.id_doctor = ds.doctor_id " +
            "WHERE ds.schedule_day = :scheduleDate", nativeQuery = true)
    List<Object[]> findSchedulesByDate(@Param("scheduleDate") LocalDate scheduleDate);
}
