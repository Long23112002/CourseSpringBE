package com.example.coursel_be.security;

public class Endpoints {

    public static final String font_end_host = "http://localhost:3000";

    public static final String[] PUBLIC_GET = {
            "/api/v1/user/hello",
            "/api/v1/SSO/loginGoogle",
            "/api/v1/course/all",
            "/api/v1/lesson/get/**",
    };

    public static final String[] PUBLIC_POST = {
            "/api/v1/user/register",
            "/api/v1/user/login",
    };

    public static final String[] ADMIN_POST = {
            "/api/v1/course/save",
            "/api/v1/course/delete/**",
            "/api/v1/course/addUserToCourse",
            "/api/v1/lesson/save",
            "/api/v1/chapter/save",
    };

    public static final String[] ADMIN_GET = {
            "/api/v1/course/get/**",
            "/api/v1/course/all",
            "/api/v1/user/allUsers",
    };

    public static final String[] ADMIN_PUT = {
            "/api/v1/course/update/**",
            "/api/v1/course/changeStatus/**",
            "/api/v1/chapter/update",
            "/api/v1/lesson/update",
    };


    public static final String[] CUSTOMER_GET = {
            "/api/v1/course/get/**",
            "/api/v1/course/all",
    };

    public static final String[] CUSTOMER_POST = {
            "/api/v1/user/addUserCourse",
            "/api/v1/blog/save",
    };

    public static final String[] CUSTOMER_PUT = {
            "/api/v1/user/updateUser",
    };


}
