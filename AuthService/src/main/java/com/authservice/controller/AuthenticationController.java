package com.authservice.controller;

import com.authservice.service.AuthenticationService;
import com.authservice.service.dtos.AuthResponseDTO;
import com.authservice.service.dtos.LoginRequestDTO;
import com.authservice.service.dtos.RegisterUserRequestDTO;
import com.authservice.service.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequest) {
        log.info("Authentication API: login requested by user: ", loginRequest.getUsername());
        AuthResponseDTO authResponseDTO = authenticationService.loginUser(loginRequest);
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        log.info("Authentication API: createUser: ", registerUserRequestDTO.getEmail());
        UserDTO userDTO = authenticationService.createUser(registerUserRequestDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}