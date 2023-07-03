package com.champ.retrospeaks.seeders;

import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Component
public class UserSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {

        User user = new User();
        user.setId(1L);
        user.setUserName("user");
        user.setEmail("user@gmail.com");
        user.setPassWord("123");
        user.setFirstName("Tony");
        user.setLastName("Stark");
        user.setCreatedDate(LocalDate.now());
        user.setGender("Male");

        userRepository.save(user);



    }
}
