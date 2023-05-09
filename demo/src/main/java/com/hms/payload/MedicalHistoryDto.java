package com.hms.payload;

import com.hms.entities.Patient;
import lombok.Data;

@Data
public class MedicalHistoryDto {
    private String allergies;
    private String previousIllness;
    private String currentMedications;
}
