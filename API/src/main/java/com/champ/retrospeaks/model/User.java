package com.champ.retrospeaks.model;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String gender;
    @CreationTimestamp
    private LocalDate createdDate;


}
