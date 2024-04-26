package com.example.coursel_be.service.Impl;

import com.example.coursel_be.entity.Role;
import com.example.coursel_be.entity.User;
import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.exceptions.ErrorCode;
import com.example.coursel_be.repository.RoleRepository;
import com.example.coursel_be.repository.UserRepository;
import com.example.coursel_be.request.LoginRequest;
import com.example.coursel_be.request.UserRequest;
import com.example.coursel_be.response.ApiResponse;
import com.example.coursel_be.response.JwtResponse;
import com.example.coursel_be.service.JwtService;
import com.example.coursel_be.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    @Transactional
    public boolean saveUser(UserRequest userRequest) {
        try {
            if (userRepository.existsByUserName(userRequest.getUserName())) {
                throw new AppException(ErrorCode.USER_ALREADY_EXISTS);
            }

//            if (userRepository.findByEmail(userRequest.getEmail())) {
//                throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
//            }

            User user = new User();
            String encodePassword = passwordEncoder.encode(userRequest.getPassword());
            user.setPassword(encodePassword);
            user.setAvatar("");
            user.setFacebookId("");
            user.setGoogleId("");
            List<Role> roleList = new ArrayList<>();
            roleList.add(roleRepository.findByRoleName("CUSTOMER"));
            user.setListRoles(roleList);
            user.setPhone(userRequest.getPhone());
            user.setGender(userRequest.getGender());
            user.setEmail(userRequest.getEmail());
            user.setFullName(userRequest.getFullName());
            user.setEmail(userRequest.getEmail());
            user.setUserName(userRequest.getUserName());
            user.setIsDeleted(false);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                final String jwtToken = jwtService.generateToken(loginRequest.getUserName());
                return new JwtResponse(jwtToken);
            }

        } catch (Exception e) {
            throw new AppException(ErrorCode.PASS_USERNAME_INCORRECT);
        }
        return null;
    }


}
