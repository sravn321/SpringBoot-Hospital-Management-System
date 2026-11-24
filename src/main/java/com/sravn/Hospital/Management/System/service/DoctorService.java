package com.sravn.Hospital.Management.System.service;

import com.sravn.Hospital.Management.System.dto.DoctorReqDTO;
import com.sravn.Hospital.Management.System.dto.DoctorResDTO;
import com.sravn.Hospital.Management.System.entity.Doctor;
import com.sravn.Hospital.Management.System.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepo;

    public DoctorResDTO createDoctor(DoctorReqDTO request) {
        Doctor doctor = new Doctor();
        doctor.setName(request.name());
        doctor.setSpecialization(request.specialization());
        Doctor saved_doctor = doctorRepo.save(doctor);
        return mapToDoctorResDTO(saved_doctor);
    }

    public DoctorResDTO mapToDoctorResDTO(Doctor doctor){
        return new DoctorResDTO(                doctor.getId(),          doctor.getName(),                doctor.getSpecialization());
    }

    public List<DoctorResDTO> getAllDoctors() {
        return doctorRepo.findAll().stream().map(doctor -> mapToDoctorResDTO(doctor)).toList();
    }

    public DoctorResDTO getDoctor(Long id) {
        Optional<Doctor> op = doctorRepo.findById(id);
        if(op.isEmpty()){
            throw new RuntimeException("Doctor not found with given id : "+id);
        }
        Doctor doctor = op.get();

        return mapToDoctorResDTO(doctor);
    }

    public List<DoctorResDTO> getAllDoctorsBySpecialization(String specialization) {
        return doctorRepo.findBySpecializationIgnoreCase(specialization).stream().map(doctor->mapToDoctorResDTO(doctor)).toList();
    }

    public DoctorResDTO updateDoctor(DoctorReqDTO request, Long id) {
        Optional<Doctor> op = doctorRepo.findById(id);
        if(op.isEmpty()){
            throw new RuntimeException("Doctor not found with given id : "+id);
        }
        Doctor doctor = op.get();
        doctor.setName(request.name());
        doctor.setSpecialization(request.specialization());

        Doctor updated_doctor = doctorRepo.save(doctor);
        return mapToDoctorResDTO(updated_doctor);
    }

    public void deleteDoctor(Long id) {
        if(!doctorRepo.existsById(id)){
            throw new RuntimeException("Doctor not found with ID: " + id);
        }
        doctorRepo.deleteById(id);
    }


}
