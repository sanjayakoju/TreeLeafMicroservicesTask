package com.authservice.service;


import com.authservice.service.dtos.AuthResponseDTO;
import com.authservice.service.dtos.LoginRequestDTO;
import com.authservice.service.dtos.RegisterUserRequestDTO;
import com.authservice.service.dtos.UserDTO;

public interface AuthenticationService {

    AuthResponseDTO loginUser(LoginRequestDTO loginRequest);

    UserDTO createUser(RegisterUserRequestDTO registerUserRequestDTO);

}
