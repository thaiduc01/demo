package com.example.demochauluc.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) 
    throws IOException, ServletException
    {
        response.sendError( SC_UNAUTHORIZED ,"Yêu cầu trái phép! vui lòng kiểm tra thông tin đăng nhập của bạn và thử lại");
    }
}


