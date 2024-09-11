package com.blubank.doctorappointment.model.dto;

import com.blubank.doctorappointment.model.entity.Doctor;
import com.blubank.doctorappointment.model.entity.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "Appointment Model Information")
@Data
public class AppointmentDto {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateAppointment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDateAppointment;
    private PatientDto patientdto;
    private DoctorDto doctordto;
}
