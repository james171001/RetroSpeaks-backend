package com.champ.retrospeaks.repository;

import com.champ.retrospeaks.model.Category;
import com.champ.retrospeaks.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    boolean existsByName(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

    List<Group> findByCategory(Category category);
    List<Group> findByUsers_Id(Long userId);

}
