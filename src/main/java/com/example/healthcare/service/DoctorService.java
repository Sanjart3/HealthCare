package com.example.healthcare.service;

import com.example.healthcare.dto.AuthDTO;
import com.example.healthcare.entity.Doctor;
import com.example.healthcare.entity.Patient;
import com.example.healthcare.exception.AppBadRequestException;
import com.example.healthcare.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PatientService patientService;

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

    public Doctor authentication(AuthDTO authDTO) {
        Doctor doctor = doctorRepository.findByEmail(authDTO.getUsername()).get();
        if (passwordEncoder.matches(authDTO.getPassword(), doctor.getPassword())){
            return doctor;
        } else {
            throw new UsernameNotFoundException("User Not Found!");
        }
    }

    public List<Patient> getPatients(Long id) {
        return patientService.getAllPatients();
    }
}
