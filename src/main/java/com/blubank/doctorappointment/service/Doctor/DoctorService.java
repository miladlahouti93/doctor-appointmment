package com.blubank.doctorappointment.service.Doctor;

import com.blubank.doctorappointment.model.dto.DoctorDto;

public interface DoctorService {
    DoctorDto registerDoctor(DoctorDto doctorDto);
    DoctorDto findById(Long id);
}
