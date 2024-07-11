package com.ecommerce.babystore.repository;

import com.ecommerce.babystore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
   Optional<Role> findByRoleName(String name);
}
