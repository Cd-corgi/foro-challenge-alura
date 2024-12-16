package com.challenge.forochallenge.service;

import com.challenge.forochallenge.persistence.entity.User;

public interface IJwtService {

  String extractUsername(String token);

  String generateToken(User user);

  Boolean isTokenValid(String token, User user);
}
