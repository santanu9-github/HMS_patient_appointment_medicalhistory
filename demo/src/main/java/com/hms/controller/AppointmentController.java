package com.hms.controller;

import com.hms.entities.Appointment;
import com.hms.payload.AppointmentDto;
import com.hms.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/patient/{patientId}/appointment")
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentDto appointmentDto,@PathVariable("patientId")long patientId) {
        AppointmentDto dto = appointmentService.createAppointment(appointmentDto,patientId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/patient/{patientId}/appointment")
    public ResponseEntity<List<AppointmentDto>> getAllAppointmentsByPatientId(@PathVariable("patientId") long patientId) {
        List<AppointmentDto> appointments = appointmentService.allAppointmentByPatientId(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
    @GetMapping("/appointment/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable("id")long id){
        AppointmentDto appointmentById = appointmentService.getAppointmentById(id);
        return new ResponseEntity<>(appointmentById,HttpStatus.OK);
    }
    @DeleteMapping("/patient/{patientId}/appointment/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id")long id,@PathVariable("patientId")long patientId){
        appointmentService.deleteById(id,patientId);
        return new ResponseEntity<>("appointment deleted successfully",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/patient/{patientId}/appointment/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@RequestBody AppointmentDto appointmentDto,
                                                            @PathVariable("id") long id,@PathVariable("patientId")long patientId){
        AppointmentDto dto = appointmentService.updatePatient(appointmentDto, id, patientId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}