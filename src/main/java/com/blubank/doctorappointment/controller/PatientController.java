package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.model.dto.PatientDto;
import com.blubank.doctorappointment.model.mapper.PatientMapper;
import com.blubank.doctorappointment.service.Patient.PatientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private PatientServiceImpl patientService;

//    @Operation(
//            summary = "Register Patient",
//            description = "Register Patient"
//    )
//    @ApiResponses(
//            value = {
//                    @ApiResponse(responseCode = "200", description = "Register Patient successfully",
//                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))}),
//            }
//    )
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PatientDto> save(@Valid @RequestBody PatientDto patientDto){
       return ResponseEntity.status(HttpStatus.CREATED).body(patientService.registerPatient(patientDto));
    }

    @Operation(
            summary = "Find Patient",
            description = "Find Patient By Name And Contact"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Find Patient successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Patient Not Found")
            }
    )
    @GetMapping("find/{name}/{contact}")
    public ResponseEntity<PatientDto> findByNameAndContact(@PathVariable String name,@PathVariable String contact){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.findByNameAndContact(name, contact));
    }

    @Operation(
            summary = "Find Patient",
            description = "Find Patient By Contact"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Find Patient successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Patient Not Found")
            }
    )
    @GetMapping("find/{contact}")
    public ResponseEntity<PatientDto> findByContact(@PathVariable String contact){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.findByContact(contact));
    }
}
