package com.example.coursel_be.request;

import com.example.coursel_be.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {


    private String userName;

    private String fullName;

    private String password;

    private String email;

    private String avatar;

    private String gender;

    private String phone;

    private String facebookId;

    private String googleId;

    private Boolean isDeleted;


}
