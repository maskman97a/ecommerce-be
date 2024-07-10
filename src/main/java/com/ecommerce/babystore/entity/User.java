package com.ecommerce.babystore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String avatar;

    private String phoneNumber;

    private String fullName;

    private LocalDate birthDay;

    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private Account account;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Session> session;

    private ERole eRole;

}
