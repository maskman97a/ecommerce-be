package com.ecommerce.babystore.service;

import com.ecommerce.babystore.dto.request.RegisterRequest;
import com.ecommerce.babystore.entity.Account;
import com.ecommerce.babystore.entity.User;
import com.ecommerce.babystore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User createUser(RegisterRequest request, Account account){
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .account(account)
                .build();
        return userRepository.save(user);
    }
}
