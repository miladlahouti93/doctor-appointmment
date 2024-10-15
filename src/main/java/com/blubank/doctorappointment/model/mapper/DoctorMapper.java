package com.blubank.doctorappointment.model.mapper;

import com.blubank.doctorappointment.model.dto.DoctorDto;
import com.blubank.doctorappointment.model.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface DoctorMapper {


   DoctorDto toDto(Doctor doctor);

   @Mappings({
           @Mapping(source = "doctorId", target = "doctorId", ignore = true)
   })
   Doctor toEntity(DoctorDto doctorDto);
}
