package com.champ.retrospeaks.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class Category {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String categoryID;
}
