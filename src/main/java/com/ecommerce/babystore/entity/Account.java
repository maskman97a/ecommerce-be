package com.ecommerce.babystore.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;
    private boolean isActived;
    private Date createdAt;
    private Date updatedAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;



}
