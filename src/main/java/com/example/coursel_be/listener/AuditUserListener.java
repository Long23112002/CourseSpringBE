package com.example.coursel_be.listener;

import com.example.coursel_be.entity.User;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Calendar;

public class AuditUserListener {
    @PrePersist
    private void onCreate(User entity) {
        entity.setCreatedDate(getCurrentTime());
        entity.setUpdatedDate(getCurrentTime());
    }

    @PreUpdate
    private void onUpdate(User entity) {
        entity.setUpdatedDate(getCurrentTime());
    }


    private long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
