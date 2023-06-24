package com.champ.retrospeaks.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int ownerID;
    private String name;
    private String description;
    @CreationTimestamp
    private LocalDate createdDate;
    private int communityCategoryID;
}
