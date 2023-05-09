package com.hms.service.impl;

import com.hms.entities.Appointment;
import com.hms.entities.MedicalHistory;
import com.hms.entities.Patient;
import com.hms.exception.ResourceNotFoundException;
import com.hms.payload.AppointmentDto;
import com.hms.payload.MedicalHistoryDto;
import com.hms.repository.MedicalHistoryRepository;
import com.hms.repository.PatientRepository;
import com.hms.service.MedicalHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    MedicalHistoryRepository medicalHistoryRepo;
    ModelMapper modelMapper;
    PatientRepository patientRepository;

    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepo, ModelMapper modelMapper,PatientRepository patientRepository) {
        this.medicalHistoryRepo = medicalHistoryRepo;
        this.modelMapper = modelMapper;
        this.patientRepository=patientRepository;
    }

    @Override
    public MedicalHistoryDto createMedicalHistory(MedicalHistoryDto medicalHistoryDto, long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("patient", "id", patientId));
        MedicalHistory medicalHistory = mapToEntity(medicalHistoryDto);
        //set appointment at particular patient
        medicalHistory.setPatient(patient);
        MedicalHistory saveMedicalHistory = medicalHistoryRepo.save(medicalHistory);
        return mapToDto(saveMedicalHistory);
    }

    @Override
    public List<MedicalHistoryDto> medicalHistoryByPatientId(long patientId) {
        patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("patient", "id", patientId));
        List<MedicalHistory> medicalHistories = medicalHistoryRepo.findByPatient(patientId);
        return medicalHistories.stream().map(medicalHistory -> mapToDto(medicalHistory)).collect(Collectors.toList());
    }

    @Override
    public MedicalHistoryDto updateMedicalHistory(MedicalHistoryDto medicalHistoryDto, long id, long patientId) {
        patientRepository.findById(patientId).orElseThrow(()->new ResourceNotFoundException("patient","id",patientId));
        MedicalHistory medicalHistory = medicalHistoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("appointment", "id", id));
        medicalHistory.setCurrentMedications(medicalHistoryDto.getCurrentMedications());
        medicalHistory.setAllergies(medicalHistoryDto.getAllergies());
        medicalHistory.setPreviousIllness(medicalHistoryDto.getPreviousIllness());
        MedicalHistory save = medicalHistoryRepo.save(medicalHistory);
        return mapToDto(save);
    }

    @Override
    public void deleteMedicalHistory(long id, long patientId) {
        patientRepository.findById(patientId).orElseThrow(()->new ResourceNotFoundException("patient","id",patientId));
        medicalHistoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("appointment","id",id));
        medicalHistoryRepo.deleteById(id);
    }
    private MedicalHistoryDto mapToDto(MedicalHistory saveMedicalHistory) {
        return modelMapper.map(saveMedicalHistory, MedicalHistoryDto.class);

    }

    private MedicalHistory mapToEntity(MedicalHistoryDto medicalHistoryDto) {
       return modelMapper.map(medicalHistoryDto, MedicalHistory.class);

    }
}
