package com.example.coursel_be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lessons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lessons")
    private Long id;

    @Column(name = "lessons_title")
    private String title;

    @Column(name = "lessons_content")
    private String content;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "lessons_video_url")
    private String videoUrl;

    @Column(name = "lessons_sequence")
    private Integer lessonSequence;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne( cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_course" , nullable = false)
    private Course course;


    @OneToMany(mappedBy = "lessons", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH}
    )
    private List<Comments> listComments;

}
