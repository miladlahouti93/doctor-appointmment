package com.blubank.doctorappointment.service.Patient;

import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.model.entity.Patient;
import com.blubank.doctorappointment.model.mapper.PatientMapper;
import com.blubank.doctorappointment.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        patient.setEmailAddress(patientDto.getEmailAddress());
        Patient registerPatient=patientRepository.save(patient);
        return patientMapper.toDto(registerPatient);
    }

    @Override
    public PatientDto findByNameAndContact(String name, String contact) {
        return patientRepository.findByNameAndContact(name,contact);
    }

    @Override
    public PatientDto findByContact(String contact) {
        return patientRepository.findByContact(contact);
    }
}
