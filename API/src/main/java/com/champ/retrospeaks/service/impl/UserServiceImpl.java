package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
