package com.blubank.doctorappointment.service.Appointment;

import com.blubank.doctorappointment.exception.NotAcceptableException;
import com.blubank.doctorappointment.exception.NotFoundException;
import com.blubank.doctorappointment.exception.NotCorrectArgumentException;
import com.blubank.doctorappointment.model.dto.AppointmentDto;
import com.blubank.doctorappointment.model.dto.DoctorDto;
import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.model.entity.Appointment;
import com.blubank.doctorappointment.model.entity.Patient;
import com.blubank.doctorappointment.model.mapper.AppointmentMapper;
import com.blubank.doctorappointment.model.mapper.PatientMapper;
import com.blubank.doctorappointment.repository.AppointmentRepository;
import com.blubank.doctorappointment.service.Doctor.DoctorServiceImpl;
import com.blubank.doctorappointment.service.Patient.PatientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public List<AppointmentDto> addListDoctorAppointments(Long doctorId, String startDateTime, String endDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(startDateTime, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDateTime, formatter);
        List<AppointmentDto> appointmentDtos=new ArrayList<AppointmentDto>();
        if(endTime.isBefore(startTime)){
          throw new NotCorrectArgumentException("end time should be bigger than start time");
        }
        DoctorDto doctorDto =doctorService.findById(doctorId);
        while(startTime.isBefore(endTime)){
            LocalDateTime endTimeTemp = startTime.plusMinutes(30);
            AppointmentDto appointmentDto=new AppointmentDto();
            appointmentDto.setDoctor(doctorDto);
            appointmentDto.setStartDateAppointment(startTime);
            appointmentDto.setEndDateAppointment(endTimeTemp);
            appointmentDtos.add(appointmentDto);
            startTime=endTimeTemp;
        }
        List<Appointment> appointmentList=appointmentMapper.toEntityList(appointmentDtos);
       return appointmentMapper.toDtoList(appointmentRepository.saveAll(appointmentList));
    }



    @Override
    public List<AppointmentDto> findDoctorAppointment(Long doctorId) {
        List<Appointment> appointments;
        appointments=appointmentRepository.findAppointmentDoctor(doctorId);
        if(appointments!=null)
            return appointmentMapper.toDtoList(appointments);
        else
            return appointmentMapper.toDtoList(new ArrayList<Appointment>());

    }




    @Override
    public AppointmentDto selectAppointment(Long id,String name,String contact) {
        if(name.isEmpty() || contact.isEmpty())
            throw new NotCorrectArgumentException("Please Enter Name And Contact");
        PatientDto patientDto=patientService.findByNameAndContact(name,contact);
        Appointment appointment=findAppointmentById(id).get();
        if(appointment == null){
            throw new NotFoundException("there is not any appointment");
        }
        else{
            appointment.setPatient(patientMapper.toEntity(patientDto));
        }
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    @Override
    public List<AppointmentDto> findPatientIsNull() {
        List<Appointment> appointments;
        appointments =appointmentRepository.findAppontmentsNotSet();
        if(appointments != null)
        return appointmentMapper.toDtoList(appointments);
        else return new ArrayList<AppointmentDto>();
    }

    @Override
    public Optional<Appointment> findAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<AppointmentDto> findAppointmentByContact(String contact) {
        PatientDto patientDto=patientService.findByContact(contact);
        List<Appointment> appointments=appointmentRepository.findAppointmentPatient(patientDto.getPatientId());
        if(appointments != null)
        return appointmentMapper.toDtoList(appointments);
        else
            return new ArrayList<AppointmentDto>();
    }

    @Override
    public void deleteAppointmentById(Long id) {
        Optional<Appointment> appointment=appointmentRepository.findById(id);
        if(appointment.get().getPatient() !=null)
            throw new NotAcceptableException("this appointment selected");
        if(appointment.get() == null)
            throw new NotFoundException("Not Found Any Appointmet");
        else
        appointmentRepository.deleteById(id);

    }


}
