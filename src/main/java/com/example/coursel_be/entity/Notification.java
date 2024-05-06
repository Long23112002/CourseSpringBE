package com.example.coursel_be.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "created_date", updatable = false, nullable = false)
    @CreationTimestamp
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

}