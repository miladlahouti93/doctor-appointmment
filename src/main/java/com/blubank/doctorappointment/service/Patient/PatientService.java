package com.blubank.doctorappointment.service.Patient;

import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.model.entity.Patient;

public interface PatientService {
    PatientDto registerPatient(PatientDto patientDto);
    PatientDto findByNameAndContact(String name,String Contact);
    PatientDto findByContact(String Contact);
}
