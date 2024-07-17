package com.ecommerce.babystore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class RegisterRequest {

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private String role;
  private String firstName;
  private String lastName;
  private String phoneNumber;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
}
