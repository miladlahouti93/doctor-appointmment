package com.blubank.doctorappointment.model.mapper;

import com.blubank.doctorappointment.model.dto.DoctorDto;
import com.blubank.doctorappointment.model.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface DoctorMapper {

   @Mappings({
           @Mapping(source = "id", target = "id",ignore = true),
           @Mapping(source = "doctorName", target = "doctorName"),
           @Mapping(source = "doctorContact", target = "doctorContact"),
           @Mapping(source = "email", target = "email")
   })
   DoctorDto toDto(Doctor doctor);
   @Mappings({
           @Mapping(source = "id", target = "id",ignore = true),
           @Mapping(source = "doctorName", target = "doctorName"),
           @Mapping(source = "doctorContact", target = "doctorContact"),
           @Mapping(source = "email", target = "email")
   })
   Doctor toEntity(DoctorDto doctorDto);
}
