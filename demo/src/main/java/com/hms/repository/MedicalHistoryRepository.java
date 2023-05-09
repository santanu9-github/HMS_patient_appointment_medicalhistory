package com.hms.repository;

import com.hms.entities.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Long> {
    List<MedicalHistory> findByPatient(long patientId);
}
