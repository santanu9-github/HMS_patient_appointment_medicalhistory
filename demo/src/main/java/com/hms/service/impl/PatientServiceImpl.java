package com.hms.service.impl;

import com.hms.entities.Patient;
import com.hms.exception.ResourceNotFoundException;
import com.hms.payload.PatientDto;
import com.hms.repository.PatientRepository;
import com.hms.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;
    public PatientServiceImpl(PatientRepository patientRepository,ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper=modelMapper;
    }

    public PatientDto createPatient(PatientDto patientDto) {
        //generate unique patient id
//        String randomPatientId = UUID.randomUUID().toString();
//        int i = Integer.parseInt(randomPatientId);
//        patientDto.setId(i);
        //convert dto to entity
        Patient patient = mapToEntity(patientDto);
        //save the data to db
        Patient savePatient = patientRepository.save(patient);
        //convert to dto then return
        return mapToDto(savePatient);
    }

    @Override
    public PatientDto getAllPatientById(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", id));
        //convert to dto then return
        return mapToDto(patient);
    }

    @Override
    public List<PatientDto> getAllPatient(int pageNo,int pageSize) {
        //store the client input in pageable interface
         Pageable pageable=PageRequest.of(pageNo, pageSize);
         //get data from db store in page collection
        Page<Patient> patientPage = patientRepository.findAll(pageable);
        //convert to entity
        List<Patient> patients = patientPage.getContent();
        //convert to dto
        List<PatientDto> patientDtos = patients.stream().map(patient -> mapToDto(patient)).collect(Collectors.toList());
        return patientDtos;
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("patient", "id", id));
        patientRepository.deleteById(id);

    }
    @Override
    public PatientDto patientUpdate(PatientDto patientDto, long id) {
        //if id found take the entity object if not through exception
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("patient", "id", id));
        //update entity object
        patient.setName(patientDto.getName());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setGender(patientDto.getGender());
        //save the entity object to database
        Patient savePatient = patientRepository.save(patient);
        //convert entity object to dto
        PatientDto dto = mapToDto(savePatient);
        return dto;
    }

    private PatientDto mapToDto(Patient savePatient) {
        PatientDto patientDto = modelMapper.map(savePatient, PatientDto.class);
        return patientDto;
    }

    private Patient mapToEntity(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        return patient;
    }
}