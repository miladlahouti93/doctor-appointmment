package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("select a from Patient a where a.patientName=:name and a.patientContact=:contact")
    PatientDto findByNameAndContact(String name,String contact);

    @Query("select a from Patient a where a.patientContact=:contact")
    PatientDto findByContact(String contact);
}

