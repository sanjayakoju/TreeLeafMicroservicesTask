package com.authservice.service.dtos;

import lombok.Data;

@Data
public class RegisterUserRequestDTO {

    private String fullName;

    private String email;

    private String password;
}