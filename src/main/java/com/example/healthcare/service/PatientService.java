package com.example.healthcare.service;

import com.example.healthcare.config.PasswordEncoderConfig;
import com.example.healthcare.dto.AuthDTO;
import com.example.healthcare.dto.SignUpRequest;
import com.example.healthcare.entity.Patient;
import com.example.healthcare.exception.AppBadRequestException;
import com.example.healthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Patient getPatientById(long id) {
        return patientRepository.findById(id).orElseThrow(()->{
            throw new AppBadRequestException("User not found");
        });
    }

    public Patient createPatient(SignUpRequest patient) {
        Patient savingPatient = fromDTO(patient);
        Patient savedPatient = patientRepository.save(savingPatient);
        return savedPatient;
    }

    public Patient updatePatient(long id, Patient patient) {
        Patient existedPatient = getPatientById(id);
        if (patient.getFirstName() != null) existedPatient.setFirstName(patient.getFirstName());
        if (patient.getLastName() != null) existedPatient.setLastName(patient.getLastName());
        if (patient.getGender() != null) existedPatient.setGender(patient.getGender());
        if (patient.getDateOfBirth() != null) existedPatient.setDateOfBirth(patient.getDateOfBirth());
        if (patient.getAddress() != null) existedPatient.setAddress(patient.getAddress());
        if (patient.getEmail() != null) existedPatient.setEmail(patient.getEmail());
        if (patient.getPhone() != null) existedPatient.setPhone(patient.getPhone());
        if (patient.getAppointments() != null) existedPatient.setAppointments(patient.getAppointments());
        if (patient.getPassword() != null) existedPatient.setPassword(patient.getPassword());
        patientRepository.save(existedPatient);
        return existedPatient;
    }


    public Patient fromDTO(SignUpRequest signupRequest){
        Patient patient = new Patient();
        patient.setFirstName(signupRequest.getFirstName());
        patient.setLastName(signupRequest.getLastName());
        patient.setEmail(signupRequest.getEmail());
        patient.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        patient.setGender(signupRequest.getGender());
        patient.setDateOfBirth(signupRequest.getDateOfBirth());
        return patient;
    }

    public Patient findByEmail(String email) {
        return patientRepository.findByEmail(email).get();
    }

    public Patient authentication(AuthDTO authDTO){
        System.out.println(passwordEncoder.encode("sanjarbek"));
        Patient patient = patientRepository.findByEmail(authDTO.getUsername()).get();
        if (passwordEncoder.matches(authDTO.getPassword(), patient.getPassword())){
            return patient;
        } else {
            throw new UsernameNotFoundException("User Not Found!");
        }
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
