package com.example.coursel_be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "avatar")
    private String avatar;

    @Column(name="gender")
    private String gender;

    @Column(name="phone")
    private String phone;

    @Column(name="facebook_id")
    private String facebookId;

    @Column(name="google_id")
    private String googleId;

    @Column(name="status")
    private Boolean isDeleted;


    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }


    @ManyToMany(fetch = FetchType.EAGER , cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> listRoles;


    @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Blog> listBlogs;

    @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Enrolments> listEnrolments;

    @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comments> listComments;

    @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseHistory> listPurchaseHistory;

    @OneToMany(mappedBy = "user")
    private List<UserCourse> userCourses;

}
