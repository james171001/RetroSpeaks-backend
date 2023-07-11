package com.champ.retrospeaks.service;

import com.champ.retrospeaks.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long userId);
    public String getUserEmailById(Long userId);
    Optional<User> findUserByUsername(String username);
    Optional<User> findByEmail(String email);

}
