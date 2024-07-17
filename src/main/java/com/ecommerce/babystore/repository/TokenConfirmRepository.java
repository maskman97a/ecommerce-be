package com.ecommerce.babystore.repository;

import com.ecommerce.babystore.entity.TokenConfirm;
import com.ecommerce.babystore.entity.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenConfirmRepository  extends JpaRepository<TokenConfirm, Long> {
    Optional<TokenConfirm> findByTokenAndTokenType(String token, TokenType tokenType);
}
