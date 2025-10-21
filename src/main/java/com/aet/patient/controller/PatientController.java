package com.aet.patient.controller;

import com.aet.patient.dto.PatientDto;
import com.aet.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:5173")
public class PatientController {

  @Autowired
  private PatientService patientService;

  @PostMapping
  public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientDto dto) {
    return ResponseEntity.ok(patientService.createPatient(dto));
  }

  @GetMapping
  public ResponseEntity<List<PatientDto>> getAllPatients() {
    return ResponseEntity.ok(patientService.getAllPatients());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
    return ResponseEntity.ok(patientService.getPatientById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientDto dto) {
    return ResponseEntity.ok(patientService.updatePatient(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePatient(@PathVariable Long id){
    patientService.deletePatient(id);
    return ResponseEntity.ok().build();
  }
}
