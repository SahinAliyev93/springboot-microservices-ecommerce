package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String orderId;

    private BigDecimal amount;

    private String status;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = "PENDING";
        }
    }
}
