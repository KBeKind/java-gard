package com.example.javagarden.service;

import com.example.javagarden.controllers.AuthenticationController;
import com.example.javagarden.data.UserRepository;
import com.example.javagarden.models.User;
import com.example.javagarden.models.UserGardenData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserGardenDataService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationController authenticationController;

    public UserGardenData getUserGardenData(HttpServletRequest request){

        HttpSession session = request.getSession();
        Integer userId = authenticationController.getUserFromSession(session).getId();

        Optional<User> userResult = userRepository.findById(userId);

        if (userResult.isEmpty()) {
            return null;
        }


        User user = userResult.get();

        UserGardenData userGardenData = user.getUserGardenData();

        return userGardenData;

    }


}
