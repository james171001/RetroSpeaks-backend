package com.champ.retrospeaks.repository;

import com.champ.retrospeaks.model.Group;
import com.champ.retrospeaks.model.Group.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    boolean existsByName(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
    List<Group> findByUsers_Id(Long userId);
//    Optional<Group> findByCategoryType(CategoryType categoryType);
}
