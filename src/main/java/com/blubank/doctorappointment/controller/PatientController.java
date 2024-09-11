package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.model.mapper.PatientMapper;
import com.blubank.doctorappointment.service.Patient.PatientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private PatientServiceImpl patientService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto save(@Valid @RequestBody PatientDto patientDto){
       return patientService.registerPatient(patientDto);
    }

    @GetMapping("find/{name}/{contact}")
    public PatientDto findByNameAndContact(@PathVariable String name,@PathVariable String contact){
        return patientService.findByNameAndContact(name,contact);
    }

    @GetMapping("find/{contact}")
    public PatientDto findByContact(@PathVariable String contact){
        return patientService.findByContact(contact);
    }
}
