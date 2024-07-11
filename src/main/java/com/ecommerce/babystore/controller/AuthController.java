package com.ecommerce.babystore.controller;


import com.ecommerce.babystore.dto.request.RegisterRequest;
import com.ecommerce.babystore.dto.response.BaseResponse;
import com.ecommerce.babystore.dto.response.VerifyAccountResponse;
import com.ecommerce.babystore.entity.Account;
import com.ecommerce.babystore.entity.Role;
import com.ecommerce.babystore.entity.User;
import com.ecommerce.babystore.exception.BusinessException;
import com.ecommerce.babystore.repository.AccountRepository;
import com.ecommerce.babystore.repository.RoleRepository;
import com.ecommerce.babystore.repository.UserRepository;
import com.ecommerce.babystore.service.AccountService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/v1")
public class AuthController {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> registerAccount(@RequestBody RegisterRequest request) throws MessagingException, UnsupportedEncodingException {
        String mess =  accountService.createAccount(request);
        return ResponseEntity.ok().body(new BaseResponse(200,mess));
    }

    @GetMapping("/verify-account")
    public ResponseEntity<BaseResponse> verifyAccount(@RequestParam  String token){
        VerifyAccountResponse data = accountService.verifyAccount(token);
        return ResponseEntity.ok().body(new BaseResponse(200,data.getMessage()));
    }
}
