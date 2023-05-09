package com.hms.service;

import com.hms.payload.PatientDto;

import java.util.List;

public interface PatientService {
    public PatientDto createPatient(PatientDto patientDto);

    PatientDto getAllPatientById(long id);

    List<PatientDto> getAllPatient(int pageNo,int pageSize);

    void deleteById(Long id);

    PatientDto patientUpdate(PatientDto patientDto, long id);
}

