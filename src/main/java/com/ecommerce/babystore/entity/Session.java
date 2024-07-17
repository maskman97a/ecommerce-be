package com.ecommerce.babystore.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "tbl_session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_session_seq")
    @SequenceGenerator(name = "tbl_session_seq", sequenceName = "tbl_session_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonManagedReference
    private Account account;

    @Column(name = "token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "login_time")
    private Date loginTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "logout_time")
    private Date logoutTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expired;
}
