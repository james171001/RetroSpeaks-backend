package com.champ.retrospeaks.repository;

import com.champ.retrospeaks.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
}
