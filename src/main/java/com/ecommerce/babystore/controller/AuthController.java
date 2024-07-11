package com.ecommerce.babystore.controller;

import com.ecommerce.babystore.dto.request.SignupRequest;
import com.ecommerce.babystore.dto.response.BaseResponse;
import com.ecommerce.babystore.entity.Account;
import com.ecommerce.babystore.entity.Role;
import com.ecommerce.babystore.entity.User;
import com.ecommerce.babystore.exception.BusinessException;
import com.ecommerce.babystore.repository.AccountRepository;
import com.ecommerce.babystore.repository.RoleRepository;
import com.ecommerce.babystore.repository.UserRepository;
import com.ecommerce.babystore.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try{
            User user = User.builder()
                    .firstName(signUpRequest.getFirstName())
                    .lastName(signUpRequest.getLastName())
                    .phoneNumber(signUpRequest.getPhoneNumber())
                    .account(accountService.createAccount(signUpRequest))
                    .build();
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (BusinessException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(500,e.getMessage()));
        }

    }
}
