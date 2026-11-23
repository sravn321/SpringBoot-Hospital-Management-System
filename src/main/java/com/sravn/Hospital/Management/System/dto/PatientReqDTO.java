package com.sravn.Hospital.Management.System.dto;

import jakarta.validation.constraints.NotBlank;

public record PatientReqDTO(
        @NotBlank
        String name,
        int age,
        @NotBlank
        String phoneno,
        String email
) {
}
