package com.blubank.doctorappointment.service.Patient;

import com.blubank.doctorappointment.exception.NotFoundException;
import com.blubank.doctorappointment.model.dto.AppointmentDto;
import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.model.entity.Patient;
import com.blubank.doctorappointment.model.mapper.AppointmentMapper;
import com.blubank.doctorappointment.model.mapper.PatientMapper;
import com.blubank.doctorappointment.repository.AppointmentRepository;
import com.blubank.doctorappointment.repository.PatientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    @Override
    public PatientDto registerPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setPatientName(patientDto.getPatientName());
        patient.setPatientContact(patientDto.getPatientContact());
        patient.setPatientEmailAddress(patientDto.getPatientEmailAddress());
        Patient registerPatient=patientRepository.save(patient);
        return patientMapper.toDto(registerPatient);
    }

    @Override
    public PatientDto findByNameAndContact(String name, String contact) {
        return patientMapper.toDto(patientRepository.findByPatientNameAndPatientContact(name,contact));
    }

    @Override
    public PatientDto findByContact(String contact) {
        return patientMapper.toDto(patientRepository.findByPatientContact(contact));
    }
}
