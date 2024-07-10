package com.ecommerce.babystore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class SignupRequest {

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
  //tôi đã sai
//  private String role;
  private String fullName;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
}
