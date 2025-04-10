package com.project_sena.spring_boot;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project_sena.spring_boot.Util.Service.JWTService;
import com.project_sena.spring_boot.Util.Service.ThreadLocalService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class Filter extends OncePerRequestFilter {

    private static final Logger log = LogManager.getLogger(Filter.class);
    private final JWTService jwtService;
    private final ThreadLocalService threadLocalService;

    public Filter(JWTService jwtService, ThreadLocalService threadLocalService) {
        this.jwtService = jwtService;
        this.threadLocalService = threadLocalService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            JWTVerification(request,response);
            filterChain.doFilter(request, response);
        } finally {
            threadLocalService.clearData();
        }
    }

    private void JWTVerification(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> jwtData = new HashMap<>();
        try{
            if(!request.getRequestURI().contains("/membership")){
                String JWTToken = request.getHeader("Authorization");
                String UID = jwtService.decodeJWTToken(JWTToken);
                jwtData.put("UID",UID);
                threadLocalService.setData(jwtData);
            }
        }catch (Exception e){
            response.setStatus(HttpStatusCode.valueOf(401).value());
            response.getWriter().write(e.toString());
            response.getWriter().flush();
            throw new ServletException("Token invalid");
        }

    }
}
