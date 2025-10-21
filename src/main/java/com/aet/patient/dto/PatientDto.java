package com.aet.patient.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {
  private Long id;

  @NotEmpty(message = "First Name must not be blank")
  private String firstName;

  @NotEmpty(message = "Last Name must not be blank")
  private String lastName;

  @NotEmpty(message = "Phone Number must not be blank")
  private String phoneNumber;

  @NotEmpty(message = "Email must not be blank")
  private String email;

  @NotNull(message = "Address must be provided")
  private AddressDto address;
}
