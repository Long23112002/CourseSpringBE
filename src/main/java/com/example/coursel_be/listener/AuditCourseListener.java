package com.example.coursel_be.listener;

import com.example.coursel_be.entity.Course;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Calendar;

public class AuditCourseListener {
    @PrePersist
    private void onCreate(Course entity) {
        entity.setCreatedAt(getCurrentTime());
        entity.setUpdateAt(getCurrentTime());
    }

    @PreUpdate
    private void onUpdate(Course entity) {
        entity.setUpdateAt(getCurrentTime());
    }



    private long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }


}