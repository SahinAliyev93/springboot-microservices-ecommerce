package com.example.model;


import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String productCode;

    private Integer quantity;

    private BigDecimal price;

    private String status;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = "CREATED";
        }
    }
}
