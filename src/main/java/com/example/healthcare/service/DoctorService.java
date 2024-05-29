package com.example.healthcare.service;

import com.example.healthcare.entity.Doctor;
import com.example.healthcare.exception.AppBadRequestException;
import com.example.healthcare.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor getDoctorById(long id) {
        return doctorRepository.findById(id).orElseThrow(() -> {
            throw new AppBadRequestException("Doctor not found");
        });
    }

    public Doctor updateDoctor(long id, Doctor doctor) {
        Doctor existedDoctor = getDoctorById(id);
        existedDoctor.setFirstName(doctor.getFirstName());
        existedDoctor.setLastName(doctor.getLastName());
        existedDoctor.setEmail(doctor.getEmail());
        existedDoctor.setPhone(doctor.getPhone());
        existedDoctor.setAddress(doctor.getAddress());
        existedDoctor.setBookingPrice(doctor.getBookingPrice());
        existedDoctor.setDateOfBirth(doctor.getDateOfBirth());
        existedDoctor.setPassword(doctor.getPassword());
        return doctorRepository.save(existedDoctor);
    }
}
