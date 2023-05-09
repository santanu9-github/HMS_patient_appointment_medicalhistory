package com.hms.payload;

import lombok.Data;

import java.util.List;

@Data
public class PatientDto {
    private long id;
    private String name;
    private String dateOfBirth;
    private String gender;
    private MedicalHistoryDto medicalHistory;
    private List<AppointmentDto> appointment;
    private BillingDto billing;

}
