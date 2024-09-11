package com.blubank.doctorappointment.model.mapper;

import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.model.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface PatientMapper {

   @Mappings({
           @Mapping(source = "id", target = "id",ignore = true),
           @Mapping(source = "patientName", target = "patientName"),
           @Mapping(source = "patientContact", target = "patientContact")
   })
   PatientDto toDto(Patient patient);

   @Mappings({
           @Mapping(source = "id", target = "id",ignore = true),
           @Mapping(source = "patientName", target = "patientName"),
           @Mapping(source = "patientContact", target = "patientContact")

   })
   Patient toEntity(PatientDto patientDto);
}
