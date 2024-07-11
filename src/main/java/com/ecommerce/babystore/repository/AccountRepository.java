package com.ecommerce.babystore.repository;

import com.ecommerce.babystore.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsByEmail(String email);
}
