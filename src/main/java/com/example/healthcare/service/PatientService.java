package com.example.healthcare.service;

import com.example.healthcare.entity.Patient;
import com.example.healthcare.exception.AppBadRequestException;
import com.example.healthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient getPatientById(long id) {
        return patientRepository.findById(id).orElseThrow(()->{
            throw new AppBadRequestException("User not found");
        });
    }

    public Patient createPatient(Patient patient) {
        patientRepository.save(patient);
        return patient;
    }

    public Patient updatePatient(long id, Patient patient) {
        Patient existedPatient = getPatientById(id);
        existedPatient.setFirstName(patient.getFirstName());
        existedPatient.setLastName(patient.getLastName());
        existedPatient.setGender(patient.getGender());
        existedPatient.setDateOfBirth(patient.getDateOfBirth());
        existedPatient.setAddress(patient.getAddress());
        existedPatient.setEmail(patient.getEmail());
        existedPatient.setPhone(patient.getPhone());
        existedPatient.setAppointments(patient.getAppointments());
        existedPatient.setPassword(patient.getPassword());
        patientRepository.save(existedPatient);
        return existedPatient;
    }


}
