package com.example.coursel_be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "enrolments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Enrolments implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enrolments")
    private Long id;

    @Column(name = "progress")
    private BigDecimal progress;


    @Column(name = "enrollment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enrollmentDate;

    @ManyToMany(fetch = FetchType.LAZY , cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(name = "enrolments_course",
            joinColumns = @JoinColumn(name = "id_enrolments"),
            inverseJoinColumns = @JoinColumn(name = "id_course"))
    private List<Course> listCourse;


    @ManyToOne( cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_user" , nullable = false)
    private User user;

}
