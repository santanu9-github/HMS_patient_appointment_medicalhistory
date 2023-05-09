package com.hms.service;

import com.hms.entities.Appointment;
import com.hms.payload.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    AppointmentDto createAppointment(AppointmentDto appointmentDto,long patientId);

    List<AppointmentDto> allAppointmentByPatientId(long patientId);
    public AppointmentDto getAppointmentById(long id);
    public void deleteById(long id,long patientId);

    AppointmentDto updatePatient(AppointmentDto appointmentDto, long id,long patientId);
}
