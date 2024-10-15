package com.blubank.doctorappointment.service.Doctor;

import com.blubank.doctorappointment.exception.NotFoundException;
import com.blubank.doctorappointment.model.dto.DoctorDto;
import com.blubank.doctorappointment.model.entity.Doctor;
import com.blubank.doctorappointment.model.mapper.DoctorMapper;
import com.blubank.doctorappointment.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorDto registerDoctor(DoctorDto doctorDto) {
       Doctor doctor = new Doctor();
       doctor.setDoctorName(doctorDto.getDoctorName());
       doctor.setDoctorContact(doctorDto.getDoctorContact());
       doctor.setDoctorEmail(doctorDto.getDoctorEmail());
       Doctor savedDoctor=doctorRepository.save(doctor);
       return doctorMapper.toDto(savedDoctor);
   }

    @Override
    public DoctorDto findById(Long id) {
        return doctorMapper.toDto(doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Doctor")));

    }

}
