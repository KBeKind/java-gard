package com.example.javagarden;

import com.example.javagarden.controllers.AuthenticationController;
import com.example.javagarden.data.UserRepository;
import com.example.javagarden.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthorizationFilter implements HandlerInterceptor {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;


    public AuthorizationFilter() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {



        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }


        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        // The user is logged in
        if (user != null) {
            // Check if the user has the required role
            boolean hasRole = (user.getRole().getId() == 2);
            if (hasRole) {
                return true;
            }
        }

        // The user is NOT logged in or does not have the required role
        response.sendRedirect("/oops");
        return false;
    }
    private static final List<String> adminList = Arrays.asList("/admin");
    private static boolean isWhitelisted(String path) {
        for (String pathRoot : adminList) {
            if (!path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }
}
