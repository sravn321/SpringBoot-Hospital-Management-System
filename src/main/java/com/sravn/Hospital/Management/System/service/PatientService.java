package com.sravn.Hospital.Management.System.service;

import com.sravn.Hospital.Management.System.dto.PatientReqDTO;
import com.sravn.Hospital.Management.System.dto.PatientResDTO;
import com.sravn.Hospital.Management.System.entity.Patient;
import com.sravn.Hospital.Management.System.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepo;

    public List<PatientResDTO> getAllPatients() {
        List<PatientResDTO> Converted_patients = patientRepo.findAll().stream().map(patient -> mapToPatientResDTO(patient) ).toList();
        return Converted_patients;
    }



    public PatientResDTO mapToPatientResDTO(Patient patient){
        return new PatientResDTO(patient.getId(), patient.getName(),patient.getAge(), patient.getPhoneno(), patient.getEmail());
    }

    public PatientResDTO createPatient(@Valid PatientReqDTO patientRequest) {
        Patient patient = new Patient();
        patient.setName(patientRequest.name());
        patient.setAge(patientRequest.age());
        patient.setPhoneno(patientRequest.phoneno());
        patient.setEmail(patientRequest.email());

        Patient saved_patient = patientRepo.save(patient);
        return mapToPatientResDTO(saved_patient);
    }

    public PatientResDTO getPatientById(Long id) {
        Optional<Patient> op = patientRepo.findById(id);
        if(op.isEmpty()){
            throw new RuntimeException("Patient not found with the provided id : "+ id);
        }
        Patient patient = op.get();
        return mapToPatientResDTO(patient);
    }


    public PatientResDTO updatePatient(PatientReqDTO request, Long id) {
        Optional<Patient> op = patientRepo.findById(id);
        if(op.isEmpty()){
            throw new RuntimeException("Patient not found with the provided id : "+ id);
        }
        Patient patient = op.get();
        patient.setName(request.name());
        patient.setAge(request.age());
        patient.setPhoneno(request.phoneno());
        patient.setEmail(request.email());
        
        Patient updated_patient = patientRepo.save(patient);
        return mapToPatientResDTO(updated_patient);
    }

    public void deletePatientById(Long id) {
        Optional<Patient> op = patientRepo.findById(id);
        if(op.isEmpty()){
            throw new RuntimeException("Patient not found with the provided id : "+ id);
        }
        Patient patient = op.get();
        patientRepo.deleteById(id);
    }
}
