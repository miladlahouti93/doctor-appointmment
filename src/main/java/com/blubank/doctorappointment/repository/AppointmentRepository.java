package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.dto.AppointmentDto;
import com.blubank.doctorappointment.model.entity.Appointment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    @Query("select a from Appointment a where a.doctor.doctorId=:doctorId")
    List<Appointment> findAppointmentDoctor(@Param("doctorId") Long doctorId);

    @Query("select a from Appointment a where a.patient is null ")
    List<Appointment> findAppontmentsNotSet();

    @Query("select a from Appointment a where a.patient.patientId=:patientId")
    List<Appointment> findAppointmentPatient(@Param("patientId") Long patientId);

}
