package com.ecommerce.babystore.repository;

import com.ecommerce.babystore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.account.email =:username ")
    Optional<User> findByUsername(@Param("username") String username);

//    @Query("SELECT")
//    boolean existsByEmail(String email);
}
