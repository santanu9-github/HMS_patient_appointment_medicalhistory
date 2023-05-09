package com.hms.repository;

import com.hms.entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingRepository extends JpaRepository<Billing,Long> {
    List<Billing> findByPatientId(long PatientId);
}
