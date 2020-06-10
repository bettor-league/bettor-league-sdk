package com.bettorleague.microservice.model.security;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@Validated
public class UserCreationRequest {

    @Email
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 15)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 15)
    private String password;
}
