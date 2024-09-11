package com.blubank.doctorappointment.model.mapper;

import com.blubank.doctorappointment.model.dto.AppointmentDto;
import com.blubank.doctorappointment.model.entity.Appointment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
   AppointmentDto toDto(Appointment appointment);
   Appointment toEntity(AppointmentDto appointmentDto);
   List<Appointment> toEntityList(List<AppointmentDto> appointments);
   List<AppointmentDto> toDtoList(List<Appointment> appointments);
}
