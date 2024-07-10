package com.ecommerce.babystore.repository;

import com.ecommerce.babystore.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
}
