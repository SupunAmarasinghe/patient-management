package com.aet.patient.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
  @NotEmpty(message = "City must not be blank")
  private String city;
  @NotEmpty(message = "State must not be blank")
  private String state;
  @NotEmpty(message = "Zip Code must not be blank")
  private String zipCode;
}
