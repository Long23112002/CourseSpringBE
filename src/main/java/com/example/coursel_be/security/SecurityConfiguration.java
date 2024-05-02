package com.example.coursel_be.security;


import com.example.coursel_be.service.JwtFilterSevice;
import com.example.coursel_be.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private JwtFilterSevice jwtFilterSevice;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserSecurityService userSecurityService) {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(userSecurityService);
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                config -> config
                        .requestMatchers(HttpMethod.GET, Endpoints.PUBLIC_GET).permitAll()
                        .requestMatchers(HttpMethod.POST, Endpoints.PUBLIC_POST).permitAll()
                        .requestMatchers(HttpMethod.POST, Endpoints.ADMIN_POST).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, Endpoints.ADMIN_PUT).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, Endpoints.ADMIN_GET).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, Endpoints.CUSTOMER_GET).hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, Endpoints.CUSTOMER_POST).hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, Endpoints.CUSTOMER_PUT).hasAuthority("CUSTOMER")
        );
        http.cors(cors -> {
            cors.configurationSource(request -> {
                CorsConfiguration corsConfig = new CorsConfiguration();
                corsConfig.addAllowedOrigin("*");
                corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                corsConfig.addAllowedHeader("*");
                return corsConfig;
            });
        });

        http.addFilterBefore(jwtFilterSevice, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
