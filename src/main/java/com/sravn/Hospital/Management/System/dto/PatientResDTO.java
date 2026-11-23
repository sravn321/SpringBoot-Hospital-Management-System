package com.sravn.Hospital.Management.System.dto;

import jakarta.validation.constraints.NotBlank;

public record PatientResDTO(
        Long id,
        String name,
        int age,
        String phoneno,
        String email
) {
}
