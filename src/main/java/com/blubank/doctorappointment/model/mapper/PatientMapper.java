package com.blubank.doctorappointment.model.mapper;

import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.model.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface PatientMapper {

   PatientDto toDto(Patient patient);

   Patient toEntity(PatientDto patientDto);
}
