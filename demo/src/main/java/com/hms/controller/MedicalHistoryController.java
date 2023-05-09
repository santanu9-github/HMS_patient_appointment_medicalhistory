package com.hms.controller;

import com.hms.payload.MedicalHistoryDto;
import com.hms.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicalHistoryController {
    @Autowired
    MedicalHistoryService medicalHistoryService;
    @PostMapping("/patient/{patientId}/medicalHistory")
    public ResponseEntity<MedicalHistoryDto> createMedicalHistory(@RequestBody MedicalHistoryDto medicalHistoryDto, @PathVariable long patientId){
        MedicalHistoryDto medicalHistory = medicalHistoryService.createMedicalHistory(medicalHistoryDto, patientId);
        return new ResponseEntity<>(medicalHistory, HttpStatus.CREATED);
    }
    @GetMapping("/patient/{patientId}/medicalHistory")
    public ResponseEntity<List<MedicalHistoryDto>> byPatientId(@PathVariable long patientId){
        List<MedicalHistoryDto> medicalHistoryDtos = medicalHistoryService.medicalHistoryByPatientId(patientId);
       return new ResponseEntity<>(medicalHistoryDtos,HttpStatus.OK);
    }
    @PutMapping("/patient/{patientId}/medicalHistory/{id}")
    public ResponseEntity<MedicalHistoryDto>updateMedicalHistory(@RequestBody MedicalHistoryDto medicalHistoryDto,
                                                                 @PathVariable long patientId,@PathVariable long id){
        MedicalHistoryDto updateMedicalHistory = medicalHistoryService.updateMedicalHistory(medicalHistoryDto, id, patientId);
       return new ResponseEntity<>(updateMedicalHistory,HttpStatus.OK);
    }
    @DeleteMapping("/patient/{patientId}/medicalHistory/{id}")
    public ResponseEntity<String>deleteMedicalHistory(@PathVariable long patientId,@PathVariable long id){
        medicalHistoryService.deleteMedicalHistory(patientId,id);
        return new ResponseEntity<>("delete successfully",HttpStatus.NOT_FOUND);
    }
}
