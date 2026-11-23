package com.sravn.Hospital.Management.System.controller;

import com.sravn.Hospital.Management.System.dto.PatientReqDTO;
import com.sravn.Hospital.Management.System.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    PatientService patientService;


    @PostMapping
    public ResponseEntity<?> createPatient(@Valid @RequestBody PatientReqDTO patientRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patientRequest));
    }

    @GetMapping
    public ResponseEntity<?> getPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatient(@PathVariable Long id){
       return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@RequestBody PatientReqDTO request,@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatient(request,id));
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
         patientService.deletePatientById(id);
    }


}
