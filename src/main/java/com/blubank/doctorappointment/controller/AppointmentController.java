package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.model.dto.AppointmentDto;
import com.blubank.doctorappointment.service.Appointment.AppointmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {

    private AppointmentServiceImpl appointmentService;

    @PostMapping("/doctorTimesAppointment/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AppointmentDto> saveDoctorTimesAppointment(@PathVariable Long id,@RequestParam LocalDateTime startTime,@RequestParam LocalDateTime endTime){
        return appointmentService.addListDoctorAppointments(id,startTime,endTime);
    }

    @GetMapping("/findDoctorAppointment/{id}")
    public List<AppointmentDto> findDoctorAppointment(@PathVariable Long id){
        return appointmentService.findDoctorAppointment(id);
    }

    @DeleteMapping("/deleteAppointment/{id}")
    void deleteAppointmentById(@PathVariable Long id){
        appointmentService.deleteAppointmentById(id);
    }
    @GetMapping("/unSelectedAppointment")
    public List<AppointmentDto> unSelectedAppointment(){
        return  appointmentService.findPatientIsNull();
    }

    @PutMapping("/selectAppointment/{id}")
    public AppointmentDto selectAppointment(@PathVariable Long id,@RequestParam String name,@RequestParam String contact){
        return appointmentService.selectAppointment(id,name,contact);
    }
    @GetMapping("/selectedAppointment")
    public List<AppointmentDto> selectedAppointment(@RequestParam String contact){
        return appointmentService.findAppointmentByContact(contact);
    }


}
