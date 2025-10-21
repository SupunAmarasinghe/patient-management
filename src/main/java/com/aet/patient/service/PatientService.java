package com.aet.patient.service;

import com.aet.patient.dto.PatientDto;

import java.util.List;
import java.util.Optional;

public interface PatientService {
  PatientDto createPatient(PatientDto patient);
  List<PatientDto> getAllPatients();
  PatientDto getPatientById(Long id);
  PatientDto updatePatient(Long id, PatientDto patient);
  void deletePatient(Long id);
}
