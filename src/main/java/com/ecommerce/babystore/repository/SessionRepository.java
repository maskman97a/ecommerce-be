package com.ecommerce.babystore.repository;

import com.ecommerce.babystore.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
