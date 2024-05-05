package com.example.coursel_be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "chapter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chapter_title")
    private String title;

    @Column(name = "chapter_sequence")
    private Integer chapterSequence;

    @Column(name = "chapter_deleted")
    private Boolean deleted;

    @JsonManagedReference
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
