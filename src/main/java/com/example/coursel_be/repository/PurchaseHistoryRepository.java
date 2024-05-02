package com.example.coursel_be.repository;

import com.example.coursel_be.entity.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
}
