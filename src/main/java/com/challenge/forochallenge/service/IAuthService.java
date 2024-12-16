package com.challenge.forochallenge.service;

import com.challenge.forochallenge.persistence.dto.auth.request.LoginRequest;
import com.challenge.forochallenge.persistence.dto.auth.request.RegisterRequest;
import com.challenge.forochallenge.persistence.dto.auth.response.AuthResponse;
import com.challenge.forochallenge.persistence.dto.auth.response.RegisterResponse;

public interface IAuthService {

  AuthResponse login(LoginRequest request);

  RegisterResponse register(RegisterRequest request);
}
