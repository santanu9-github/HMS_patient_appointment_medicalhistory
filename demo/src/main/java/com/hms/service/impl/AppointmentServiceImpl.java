package com.hms.service.impl;

import com.hms.entities.Appointment;
import com.hms.entities.Patient;
import com.hms.exception.ResourceNotFoundException;
import com.hms.payload.AppointmentDto;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.PatientRepository;
import com.hms.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    ModelMapper modelMapper;
    AppointmentRepository appointmentRepo;
    PatientRepository patientRepo;

    public AppointmentServiceImpl(ModelMapper modelMapper, AppointmentRepository appointmentRepo,PatientRepository patientRepo) {
        this.modelMapper = modelMapper;
        this.appointmentRepo = appointmentRepo;
        this.patientRepo=patientRepo;
    }

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto,long patientId) {
        Patient patient = patientRepo.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("patient", "id", patientId));
        Appointment appointment = mapToEntity(appointmentDto);
        //set appointment at particular patient
        appointment.setPatient(patient);
        Appointment saveAppointment = appointmentRepo.save(appointment);
       return mapToDto(saveAppointment);
    }

    @Override
    public List<AppointmentDto> allAppointmentByPatientId(long patientId) {
        List<Appointment> appointments = appointmentRepo.findByPatientId(patientId);
        List<AppointmentDto> dto = appointments.stream().map(patient -> mapToDto(patient)).collect(Collectors.toList());
        return dto;
    }
    public AppointmentDto getAppointmentById(long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
        return mapToDto(appointment);
    }
    public void deleteById(long id,long patientId) {
        patientRepo.findById(patientId).orElseThrow(()->new ResourceNotFoundException("patient","id",patientId));
        appointmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("appointment","id",id));
        appointmentRepo.deleteById(id);
    }

    @Override
    public AppointmentDto updatePatient(AppointmentDto appointmentDto, long id,long patientId) {
        patientRepo.findById(patientId).orElseThrow(()->new ResourceNotFoundException("patient","id",patientId));
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("appointment", "id", id));
        appointment.setLocalDateTime(appointmentDto.getLocalDateTime());
        appointment.setReasonForVisit(appointmentDto.getReasonForVisit());
        Appointment saveAppointment = appointmentRepo.save(appointment);
        return mapToDto(saveAppointment);
    }

    private AppointmentDto mapToDto(Appointment saveAppointment) {
        AppointmentDto dto = modelMapper.map(saveAppointment, AppointmentDto.class);
        return dto;
    }

    private Appointment mapToEntity(AppointmentDto appointmentDto) {
        Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
        return appointment;
    }
}
