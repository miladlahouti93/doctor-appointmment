package com.blubank.doctorappointment.service.Appointment;

import com.blubank.doctorappointment.exception.NotFoundException;
import com.blubank.doctorappointment.model.dto.AppointmentDto;
import com.blubank.doctorappointment.model.dto.DoctorDto;
import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.model.entity.Appointment;
import com.blubank.doctorappointment.model.mapper.AppointmentMapper;
import com.blubank.doctorappointment.model.mapper.PatientMapper;
import com.blubank.doctorappointment.repository.AppointmentRepository;
import com.blubank.doctorappointment.service.Doctor.DoctorServiceImpl;
import com.blubank.doctorappointment.service.Patient.PatientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;
    private final DoctorServiceImpl doctorService;
    private final PatientServiceImpl patientService;
    private final AppointmentMapper appointmentMapper;
    private final PatientMapper patientMapper;
    @Override
    public List<AppointmentDto> addListDoctorAppointments(Long doctorId, LocalDateTime startTime, LocalDateTime endTime) {
        List<AppointmentDto> appointmentDtos=new ArrayList<AppointmentDto>();
        if(endTime.isBefore(startTime)){

        }
        DoctorDto doctorDto =doctorService.findById(doctorId);
        while(startTime.isBefore(endTime)){
            LocalDateTime endTimeTemp = startTime.plusMinutes(30);
            AppointmentDto appointmentDto=new AppointmentDto();
            appointmentDto.setDoctordto(doctorDto);
            appointmentDto.setStartDateAppointment(startTime);
            appointmentDto.setEndDateAppointment(endTimeTemp);
            appointmentDtos.add(appointmentDto);
            startTime=endTimeTemp;
        }
       return appointmentMapper.toDtoList(appointmentRepository.saveAll(appointmentMapper.toEntityList(appointmentDtos)));
    }



    @Override
    public List<AppointmentDto> findDoctorAppointment(Long doctorId) {
            return appointmentRepository.findAppointmentDoctor(doctorId);

    }




    @Override
    public AppointmentDto selectAppointment(Long id,String name,String contact) {
        PatientDto patientDto=patientService.findByNameAndContact(name,contact);
        Appointment appointment=findAppointmentById(id).get();
        if(appointment != null){
            appointment.setPatient(patientMapper.toEntity(patientDto));
        }
        else{
            throw new NotFoundException("there is not any appointment");
        }
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    @Override
    public List<AppointmentDto> findPatientIsNull() {
        return appointmentRepository.findAppontmentsNotSet();
    }

    @Override
    public Optional<Appointment> findAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<AppointmentDto> findAppointmentByContact(String contact) {
        PatientDto patientDto=patientService.findByContact(contact);
        return appointmentRepository.findAppointmentPatient(patientDto.getId());
    }

    @Override
    public void deleteAppointmentById(Long id) {
        appointmentRepository.deleteById(id);
    }


}
