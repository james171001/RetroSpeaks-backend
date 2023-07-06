package com.champ.retrospeaks.service;

import com.champ.retrospeaks.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long userId);
}
