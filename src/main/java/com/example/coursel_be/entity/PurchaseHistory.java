package com.example.coursel_be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "purchase_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase")
    private Long id;

    @Column(name = "purchase_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    @Column(name = "purchase_amount")
    private BigDecimal purchaseAmount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;
}
