package com.aet.patient.service;

import com.aet.patient.dto.AddressDto;
import com.aet.patient.dto.PatientDto;
import com.aet.patient.model.Address;
import com.aet.patient.model.Patient;
import com.aet.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService{

  @Autowired
  private PatientRepository repository;

  @Override
  @Transactional
  public PatientDto createPatient(PatientDto dto) {
    //Check if Patient already exist in the system
    repository.findByEmail(dto.getEmail())
        .ifPresent(existing ->
          {
            log.error("A patient with given email is already available in the system.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "A patient with given email is already available in the system.!");
          });

    Patient patient = repository.save(mapToPatientEntity(dto));
    return mapToPatientDto(patient);
  }

  @Override
  public List<PatientDto> getAllPatients() {
    List<Patient> patients = repository.findAll();
    return patients.stream().map(this::mapToPatientDto).toList();
  }

  @Override
  public PatientDto getPatientById(Long id) {
    Patient patient = repository.findById(id)
        .orElseThrow(() -> {
          log.error("Patient with ID {} Not Found.", id);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with ID:" + id + " Not Found.!");
        });
    return mapToPatientDto(patient);
  }

  @Override
  @Transactional
  public PatientDto updatePatient(Long id, PatientDto dto) {
    //Retrieve Patient by patient id
    Patient patient = repository.findById(id)
        .map(existing -> {
            existing.setFirstName(dto.getFirstName());
            existing.setLastName(dto.getLastName());
            existing.setEmail(dto.getEmail());
            existing.setPhoneNumber(dto.getPhoneNumber());
            existing.setAddress(mapToAddressEntity(dto.getAddress()));
          return existing;
        })
        .map(repository::save)
        .orElseThrow(() -> {
          log.error("Patient with ID {} Not Found.", id);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with ID:" + id + " Not Found.!");
        });

    return mapToPatientDto(patient);
  }

  @Override
  public void deletePatient(Long id) {
    repository.deleteById(id);
  }

  private Patient mapToPatientEntity(PatientDto dto){
    return Patient.builder().firstName(dto.getFirstName()).lastName(dto.getLastName()).phoneNumber(dto.getPhoneNumber())
        .email(dto.getEmail()).address(mapToAddressEntity(dto.getAddress())).build();
  }

  private Address mapToAddressEntity(AddressDto dto){
    return Address.builder().zipCode(dto.getZipCode()).state(dto.getState()).city(dto.getCity()).build();
  }

  private PatientDto mapToPatientDto(Patient patient){
    return PatientDto.builder().id(patient.getId()).firstName(patient.getFirstName()).lastName(patient.getLastName())
        .phoneNumber(patient.getPhoneNumber()).email(patient.getEmail()).address(mapToAddressDto(patient.getAddress()))
        .build();
  }

  private AddressDto mapToAddressDto(Address address){
    return AddressDto.builder().zipCode(address.getZipCode()).state(address.getState())
        .city(address.getCity()).build();
  }
}
