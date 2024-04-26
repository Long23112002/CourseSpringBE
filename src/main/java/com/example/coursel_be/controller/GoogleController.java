//package com.example.coursel_be.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/api/v1/SSO")
//@Slf4j
//public class GoogleController {
////    @GetMapping("/loginGoogle")
////    public Map<String , Object> currentUser(@AuthenticationPrincipal OAuth2AuthenticationToken oAuth2AuthenticationToken){
////        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
////    }
//    @GetMapping("/loginGoogle")
//    public String login() {
//        return "redirect:/oauth2/authorization/google";
//    }
//
//}
