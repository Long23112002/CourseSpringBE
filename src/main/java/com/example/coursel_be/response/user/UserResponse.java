package com.example.coursel_be.response.user;

import com.example.coursel_be.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponse {

    private Long id;

    private String userName;

    private String fullName;

    private String email;

    private String avatar;

    private String gender;

    private String phone;

    private Boolean isDeleted;

}
