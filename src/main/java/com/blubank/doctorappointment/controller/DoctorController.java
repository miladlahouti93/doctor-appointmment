package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.model.dto.DoctorDto;
import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.service.Doctor.DoctorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorServiceImpl doctorService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto save(@Valid @RequestBody DoctorDto doctorDto){
        return doctorService.registerDoctor(doctorDto);
    }

}
