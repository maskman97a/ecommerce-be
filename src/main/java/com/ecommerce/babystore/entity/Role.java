package com.ecommerce.babystore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Add a no-args constructor for JPA
@Entity
@Table(name = "tbl_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_role_seq")
    @SequenceGenerator(name = "tbl_role_seq", sequenceName = "tbl_role_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, name = "role_name")
    private String roleName;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, name = "created_by")
    private String createdBy;

    @Column(nullable = false, name = "updated_by")
    private String updatedBy;

    @Column(nullable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(nullable = false, name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "role")
    @JsonBackReference
    private List<Account> accountList;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
