package com.hms.service;

import com.hms.payload.MedicalHistoryDto;

import java.util.List;

public interface MedicalHistoryService {
    MedicalHistoryDto createMedicalHistory(MedicalHistoryDto medicalHistoryDto,long patientId);
     List<MedicalHistoryDto> medicalHistoryByPatientId(long patientId);
     MedicalHistoryDto updateMedicalHistory(MedicalHistoryDto medicalHistoryDto,long id,long patientId);
     void deleteMedicalHistory(long id,long patientId);

}
