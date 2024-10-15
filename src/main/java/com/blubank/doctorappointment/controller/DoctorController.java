package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.model.dto.DoctorDto;
import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.service.Doctor.DoctorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {

    private DoctorServiceImpl doctorService;

    @Operation(
            summary = "Register Doctor",
            description = "Register New Doctor"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Register Doctor successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DoctorDto.class))})
            }
    )
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto save(@Valid @RequestBody DoctorDto doctorDto){
        return doctorService.registerDoctor(doctorDto);
    }

}
