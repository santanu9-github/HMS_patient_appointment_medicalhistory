package com.hms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class Patient {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String name;
private String dateOfBirth;
private String gender;
@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,orphanRemoval = true)
private List<Appointment> appointments=new ArrayList<>();
@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,orphanRemoval = true)
private List<MedicalHistory> medicalHistories=new ArrayList<>();
@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,orphanRemoval = true)
private List<Billing> billings=new ArrayList<>();
}
