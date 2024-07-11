package com.ecommerce.babystore.service;

import com.ecommerce.babystore.dto.request.SignupRequest;
import com.ecommerce.babystore.entity.Account;
import com.ecommerce.babystore.entity.Role;
import com.ecommerce.babystore.exception.BusinessException;
import com.ecommerce.babystore.repository.AccountRepository;
import com.ecommerce.babystore.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    public Account createAccount(SignupRequest signUpRequest)
    {
        if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BusinessException("Error: Email is already in use!");
        }
        Role role = roleRepository.findByRoleName(signUpRequest.getRole()).orElse(null);
        Account account = Account.builder().email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .role(role)
                .build();

        return accountRepository.save(account);
    }
}
