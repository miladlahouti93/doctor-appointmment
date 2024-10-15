package com.blubank.doctorappointment.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Patient Data Information")
@Data
public class PatientDto {

    private Long patientId;
    private String patientName;
    private String patientContact;
    private String patientEmailAddress;
}
