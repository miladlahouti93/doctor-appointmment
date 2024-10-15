package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.model.dto.AppointmentDto;
import com.blubank.doctorappointment.service.Appointment.AppointmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "set timeSheet ",
            description = "Doctor should create work timeSheet",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Doctor input his Id,Start DateTime and End DateTime then application generate work timeSheet.",
                    content = @Content(schema = @Schema(implementation = AppointmentDto.class))
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "TimeSheet created successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Doctor Not Found")
            }
    )
    @PostMapping("/doctorTimesAppointment/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AppointmentDto> saveDoctorTimesAppointment(@PathVariable Long id,@RequestParam String startTime,@RequestParam String endTime){
        return appointmentService.addListDoctorAppointments(id,startTime,endTime);
    }

    @Operation(
            summary = "Find All Appointment Doctor",
            description = "Input  Doctor ID To Find All Appointment",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Doctor input his Id then return all appointment ",
                    content = @Content(schema = @Schema(implementation = AppointmentDto.class))
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Find Doctor Appointment successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Doctor Appointment Not Found")
            }
    )
    @GetMapping("/findDoctorAppointment/{id}")
    public List<AppointmentDto> findDoctorAppointment(@PathVariable Long id){
        return appointmentService.findDoctorAppointment(id);
    }

    @Operation(
            summary = "Delete Appointment By Id",
            description = "Input Appointment ID To Delete This Appointment",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Input Appointment Id To Delete This Appointment",
                    content = @Content(schema = @Schema(implementation = AppointmentDto.class))
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Delete Appointment successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Appointment Not Found")
            }
    )
    @DeleteMapping("/deleteAppointment/{id}")
    void deleteAppointmentById(@PathVariable Long id){
        appointmentService.deleteAppointmentById(id);
    }

    @Operation(
            summary = "Find All Appointments dont select",
            description = "Find All Appointments dont select",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Find All Appointments dont select",
                    content = @Content(schema = @Schema(implementation = AppointmentDto.class))
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Find Appointments successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Appointments Not Found")
            }
    )
    @GetMapping("/unSelectedAppointment")
    public List<AppointmentDto> unSelectedAppointment(){
        return  appointmentService.findPatientIsNull();
    }

    @Operation(
            summary = "Select Appointment By Name And Contact Patient",
            description = "Select Appointment By Name And Contact Patient",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Select Appointment By Name And Contact Patient",
                    content = @Content(schema = @Schema(implementation = AppointmentDto.class))
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Select Appointments successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDto.class))}),
            }
    )
    @PutMapping("/selectAppointment/{id}")
    public AppointmentDto selectAppointment(@PathVariable Long id,@RequestParam String name,@RequestParam String contact){
        return appointmentService.selectAppointment(id,name,contact);
    }

    @Operation(
            summary = "Find All Appointments that selected By Contact patient",
            description = "Find All Appointments that selected By Contact patient",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Find All Appointments that selected By patient.you should input contact patient",
                    content = @Content(schema = @Schema(implementation = AppointmentDto.class))
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Select Appointments successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDto.class))}),
            }
    )
    @GetMapping("/selectedAppointment")
    public List<AppointmentDto> selectedAppointment(@RequestParam String contact){
        return appointmentService.findAppointmentByContact(contact);
    }


}
