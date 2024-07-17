package com.ecommerce.babystore.service;

import com.ecommerce.babystore.dto.request.LoginRequest;
import com.ecommerce.babystore.dto.request.RegisterRequest;
import com.ecommerce.babystore.dto.response.JwtResponse;
import com.ecommerce.babystore.dto.response.VerifyAccountResponse;
import com.ecommerce.babystore.entity.*;
import com.ecommerce.babystore.exception.BusinessException;
import com.ecommerce.babystore.repository.AccountRepository;
import com.ecommerce.babystore.repository.RoleRepository;
import com.ecommerce.babystore.repository.SessionRepository;
import com.ecommerce.babystore.repository.TokenConfirmRepository;
import com.ecommerce.babystore.sercurity.jwt.JwtUtils;
import com.ecommerce.babystore.sercurity.services.UserDetailsImpl;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final TokenConfirmRepository tokenConfirmRepository;
    private final UserService userService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${server.port}")
    private int serverPort;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;
    public JwtResponse login(LoginRequest request) {
        // Tạo đối tượng xác thực
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        try {
            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String jwt = jwtUtils.generateJwtToken(authentication);
            Session session = Session.builder()
                    .account(accountRepository.findAccountByEmail(request.getEmail()))
                    .expired(new Date((new Date()).getTime() + jwtExpirationMs))
                    .loginTime(new Date())
                    .token(jwt)
                    .build();
            sessionRepository.save(session);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getFirstName(),
                    userDetails.getLastName(),
                    userDetails.getEmail(),
                    userDetails.getAuthorities().toString())).getBody();
        } catch (DisabledException e) {
            throw new RuntimeException("Tài khoản chưa được xác thực");
        } catch (AuthenticationException e) {
            throw new RuntimeException("Email hoặc mật khẩu không đúng");
        }
    }

    @Transactional
    public String createAccount(RegisterRequest request) throws MessagingException, UnsupportedEncodingException {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Error: Email is already in use!");
        }
        Account account;
        if(accountRepository.findByEmailAndStatusIsFalse(request.getEmail()).isPresent()){
            account =  accountRepository.findAccountByEmail(request.getEmail());
            account.getUser().setFirstName(request.getFirstName());
            account.getUser().setLastName(request.getLastName());
            account.setUpdatedAt(new Date());
            account.setPassword(passwordEncoder.encode(request.getPassword()));
        }else {
            Role role = roleRepository.findRoleById(1L);
            account = Account.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .role(role)
                    .status(false)
                    .build();
        }
        Account newAccount = accountRepository.save(account);
        User user = userService.createUser(request,newAccount);
        // Lưu token vào database
        TokenConfirm tokenConfirm = TokenConfirm.builder()
                .token(UUID.randomUUID().toString())
                .tokenType(TokenType.REGISTRATION)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusHours(1))
                .account(account)
                .build();
        tokenConfirmRepository.save(tokenConfirm);

        // Trả về path xác thực tài khoản
        // TODO: Link này gửi vào trong email
        String link = "http://localhost:" + serverPort + "/api/v1/verify-account?token=" +  tokenConfirm.getToken();;
        mailService.sendVerificationEmail(account, user, link);
        return "Registration successful, please check your email to verify your account.";
    }
    @Transactional
    public VerifyAccountResponse verifyAccount(String token) {
        // Tìm kiếm token trong database
        Optional<TokenConfirm> optionalTokenConfirm = tokenConfirmRepository
                .findByTokenAndTokenType(token, TokenType.REGISTRATION);

        // Kiểm tra token có tồn tại không
        if(optionalTokenConfirm.isEmpty()) {
            return VerifyAccountResponse.builder()
                    .status(false)
                    .message("Token không hợp lệ")
                    .build();
        }

        // Kiểm tra xem token đã được xác thực chưa
        TokenConfirm tokenConfirm = optionalTokenConfirm.get();
        if(tokenConfirm.getConfirmedAt() != null) {
            return VerifyAccountResponse.builder()
                    .status(false)
                    .message("Token đã được xác thực")
                    .build();
        }

        // Kiểm tra xem token đã hết hạn chưa
        if(tokenConfirm.getExpiredAt().isBefore(LocalDateTime.now())) {
            return VerifyAccountResponse.builder()
                    .status(false)
                    .message("Token đã hết hạn")
                    .build();
        }

        // Xác thực tài khoản của account
        Account account = tokenConfirm.getAccount();
        account.setStatus(true);
        accountRepository.save(account);

        // Cập nhật thời gian xác thực token
        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokenConfirmRepository.save(tokenConfirm);

        return VerifyAccountResponse.builder()
                .status(true)
                .message("Xác thực tài khoản thành công")
                .build();
    }



}
