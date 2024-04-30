package com.example.coursel_be.repository;

import com.example.coursel_be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String username);

    public boolean existsByUserName(String username);

    public boolean existsByEmail(String email);
}
