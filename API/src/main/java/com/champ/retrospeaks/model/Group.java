package com.champ.retrospeaks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    @CreationTimestamp
    private LocalDate createdDate;

    @UpdateTimestamp
    private LocalDate updatedDate;

    private Long groupOwner;

    @ManyToMany(mappedBy ="groups",cascade = CascadeType.ALL)

    private List<User> users;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type", nullable=false)
    private CategoryType categoryType;
    public enum CategoryType {
        ENTERTAINMENT,
        LIFESTYLE,
        SCIENCE_AND_TECHNOLOGY,
        HISTORY,
        BUSINESS_AND_FINANCE;


    }
}
