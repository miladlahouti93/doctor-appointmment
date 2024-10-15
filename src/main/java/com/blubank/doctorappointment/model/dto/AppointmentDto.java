package com.blubank.doctorappointment.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "Appointment Model Information")
@Data
public class AppointmentDto {

    private Long id;
    private LocalDateTime startDateAppointment;
    private LocalDateTime endDateAppointment;
    private PatientDto patient;
    private DoctorDto doctor;
}
