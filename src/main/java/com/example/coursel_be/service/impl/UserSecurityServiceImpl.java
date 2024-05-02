package com.example.coursel_be.service.impl;

import com.example.coursel_be.entity.Role;
import com.example.coursel_be.entity.User;
import com.example.coursel_be.repository.RoleRepository;
import com.example.coursel_be.repository.UserRepository;
import com.example.coursel_be.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {


    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), rolesToAuthorities(user.getListRoles()));
        return userDetail;
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
