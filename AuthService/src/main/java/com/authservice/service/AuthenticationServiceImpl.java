package com.authservice.service;


import com.authservice.model.User;
import com.authservice.repository.UserRepository;
import com.authservice.security.JwtTokenProvider;
import com.authservice.service.dtos.AuthResponseDTO;
import com.authservice.service.dtos.LoginRequestDTO;
import com.authservice.service.dtos.RegisterUserRequestDTO;
import com.authservice.service.dtos.UserDTO;
import com.commonmodule.security.enums.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDTO loginUser(LoginRequestDTO loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            String token = jwtTokenProvider.createToken(authentication);
            AuthResponseDTO authResponseDTO = new AuthResponseDTO();
            authResponseDTO.setToken(token);
            return authResponseDTO;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Bad Login Credentials");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO createUser(RegisterUserRequestDTO registerUserRequestDTO) {
        User user = new User();
        user.setUsername(registerUserRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserRequestDTO.getPassword()));
        user.setRoles(Set.of(UserRole.ROLE_USER));
        User returnedUser = userRepository.save(user);
        return mapUserToUserDTO(returnedUser);
    }


    private UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getUsername());
        return userDTO;
    }

}
