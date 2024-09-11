package com.blubank.doctorappointment.service.Appointment;

import com.blubank.doctorappointment.model.dto.AppointmentDto;
import com.blubank.doctorappointment.model.dto.DoctorDto;
import com.blubank.doctorappointment.model.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<AppointmentDto> addListDoctorAppointments(Long id, LocalDateTime startTime, LocalDateTime endTime);
    List<AppointmentDto> findDoctorAppointment(Long doctorId);
    AppointmentDto selectAppointment(Long id,String name,String Contact);
    List<AppointmentDto> findPatientIsNull();
    Optional<Appointment> findAppointmentById(Long id);
    List<AppointmentDto> findAppointmentByContact(String contact);
    void deleteAppointmentById(Long id);

}
