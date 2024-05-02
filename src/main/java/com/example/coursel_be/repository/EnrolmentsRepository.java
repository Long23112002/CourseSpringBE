package com.example.coursel_be.repository;

import com.example.coursel_be.entity.Enrolments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolmentsRepository extends JpaRepository<Enrolments, Long> {
}
