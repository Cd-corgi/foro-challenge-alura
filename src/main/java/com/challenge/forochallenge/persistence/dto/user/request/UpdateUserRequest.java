package com.challenge.forochallenge.persistence.dto.user.request;

import com.challenge.forochallenge.persistence.entity.Role;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record UpdateUserRequest(
    @NotBlank(message = "el name no puede ir vacio")
    String name,
    @DefaultValue(value = "USER")
    Role role,
    @NotBlank(message = "el name no puede ir vacio")
    String password
) {

}
