package com.example.coursel_be.security;

public class Endpoints {
    public static final String font_end_host = "http://localhost:3000";
    public static final String[] PUBLIC_GET = {
            "/api/v1/user/hello",
            "/api/v1/SSO/loginGoogle",
            "/login/oauth2/code/google"
    };
    public static final String[] PUBLIC_POST = {
            "/api/v1/user/register",
            "/api/v1/user/login",
            "/login/oauth2/code/google"
    };
}
