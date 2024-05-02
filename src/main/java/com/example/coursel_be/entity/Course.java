package com.example.coursel_be.entity;


import com.example.coursel_be.listener.AuditCourseListener;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditCourseListener.class)
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Long id;

    @Column(name = "coursel_title")
    private String title;

    @Column(name = "coursel_description")
    private String description;

    @Column(name = "coursel_price")
    private BigDecimal coursePrice;

    @Column(name = "coursel_cover")
    private String cover;

    @Column(name = "created_at" , updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Long createdAt;

    @Column(name = "create_by")
    private String createBy;


    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Long updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "deleted")
    private Boolean deleted;




    @OneToMany(mappedBy = "course", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH}
    )
    private List<Lessons> listLessons;

    @ManyToMany(fetch = FetchType.LAZY , cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(name = "enrolments_course",
            joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id_enrolments"))
    private List<Enrolments> listEnrolments;


    @OneToMany(mappedBy = "course" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseHistory> listPurchaseHistory;

    @OneToMany(mappedBy = "course")
    private List<UserCourse> courseUsers;

}
