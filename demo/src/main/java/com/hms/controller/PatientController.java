package com.hms.controller;

import com.hms.payload.PatientDto;
import com.hms.service.PatientService;
import com.hms.utility.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patient) {
        PatientDto savedPatient = patientService.createPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") long id) {
        PatientDto patientDto = patientService.getAllPatientById(id);
        return new ResponseEntity<>(patientDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatient(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                          @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        List<PatientDto> allPatient = patientService.getAllPatient(pageNo, pageSize);
        return new ResponseEntity<>(allPatient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable("id") long id) {
        patientService.deleteById(id);
        return new ResponseEntity<>("patient deleted successfully",HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> uploadPatient(@RequestBody PatientDto patientDto,@PathVariable("id")long id){
        PatientDto dto = patientService.patientUpdate(patientDto, id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}