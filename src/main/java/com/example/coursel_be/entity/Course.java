package com.example.coursel_be.entity;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "created_at" , updatable = false , nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "create_by")
    private String createBy;


    @Column(name = "update_at")
    @UpdateTimestamp
    private Date updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "deleted")
    private Boolean deleted;


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


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Chapter> chapters;

}
