package com.blubank.doctorappointment.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Doctor Model Information")
@Data
public class DoctorDto {


    private Long doctorId;
    private String doctorName;
    private String doctorContact;
    private String doctorEmail;
}
