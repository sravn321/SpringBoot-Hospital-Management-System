package com.sravn.Hospital.Management.System.controller;

import com.sravn.Hospital.Management.System.dto.DoctorReqDTO;
import com.sravn.Hospital.Management.System.dto.DoctorResDTO;
import com.sravn.Hospital.Management.System.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;


    @PostMapping
    public ResponseEntity<DoctorResDTO> createDoctor(@RequestBody DoctorReqDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(request));
    }
    @GetMapping
    public ResponseEntity<List<DoctorResDTO>> getAllDoctors(@RequestParam(required = false) String specialization){
        if(specialization != null && !specialization.isBlank()){
            return ResponseEntity.ok(doctorService.getAllDoctorsBySpecialization(specialization));
        }
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResDTO> getDoctor(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDoctor(id));
    }
//2 Get methods map to same endpoint GET /api/doctors so app crashes
//    @GetMapping
//    // localhost:8080/api/doctors?specialization=Cardiologist
//    public ResponseEntity<?> getAllDoctorsBySpecialization(@RequestParam String specialization){
//        return ResponseEntity.ok(doctorService.getAllDoctorsBySpecialization(specialization));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResDTO> updateDoctor(@RequestBody DoctorReqDTO request,@PathVariable  Long id){
        return ResponseEntity.ok(doctorService.updateDoctor(request,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
