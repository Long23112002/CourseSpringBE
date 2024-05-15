package com.example.coursel_be.request.user;

import com.example.coursel_be.infrastructure.constant.EntityProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {


//    private Long idUser;

    @NotBlank(message = "Full name is not empty")
    @Size(min = EntityProperties.MAX_LENGTH_5, max = EntityProperties.MAX_LENGTH_50, message = "Full name must be between 5 and 50 characters")
    private String fullName;

    private String avatar;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Nam|Nữ)$", message = "Gender must be 'Nam' or 'Nữ'")
    private String gender;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number format")
    private String phone;

}
