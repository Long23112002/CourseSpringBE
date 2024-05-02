package com.example.coursel_be.repository;

import com.example.coursel_be.entity.User;
import com.example.coursel_be.response.user.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String username);

    public boolean existsByUserName(String username);

    public boolean existsByEmail(String email);

//    @Query("SELECT new com.example.coursel_be.response.user.UserResponse(u.id, u.userName,u.fullName , u.email , u.avatar , u.gender , u.phone , u.isDeleted , u.listRoles, u.listBlogs, u.listEnrolments, u.listPurchaseHistory, u.userCourses) " +
//            "FROM User u LEFT JOIN FETCH u.listRoles LEFT JOIN FETCH u.listBlogs LEFT JOIN FETCH u.listEnrolments LEFT JOIN FETCH u.listComments LEFT JOIN FETCH u.listPurchaseHistory LEFT JOIN FETCH u.userCourses " +
//            "WHERE u.id = :userId")
//    UserResponse getUserInfoById(@Param("userId") Long userId);

    @Query("SELECT new com.example.coursel_be.response.user.UserResponse(u.id, u.userName, u.fullName, u.email, u.avatar, u.gender, u.phone, u.isDeleted) " +
            "FROM User u ")
    List<UserResponse> getAllUser();

}
