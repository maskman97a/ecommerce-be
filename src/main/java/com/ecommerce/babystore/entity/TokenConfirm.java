package com.ecommerce.babystore.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tbl_token_confirm")
public class TokenConfirm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_token_confirm_seq_generater")
    @SequenceGenerator(name = "tbl_token_confirm_seq_generater", sequenceName = "tbl_token_confirm_seq", allocationSize = 1)
    Long id;

    @Column(name = "token")
    String token; // chuỗi token

    @Enumerated(EnumType.STRING)
    TokenType tokenType; // loại token

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    LocalDateTime createdAt; // thời gian tạo

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_at")
    LocalDateTime expiredAt; // thời gian hết hạn

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "confirm_at")
    LocalDateTime confirmedAt; // thời gian xác thực

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account; // token này của user nào
}

