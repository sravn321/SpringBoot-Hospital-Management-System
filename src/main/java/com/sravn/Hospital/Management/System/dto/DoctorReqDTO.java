package com.sravn.Hospital.Management.System.dto;

import jakarta.validation.constraints.NotBlank;

public record DoctorReqDTO(
        @NotBlank
        String name,
        @NotBlank
        String specialization
) {
}
