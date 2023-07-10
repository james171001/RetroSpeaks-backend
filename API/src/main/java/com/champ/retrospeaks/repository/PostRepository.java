package com.champ.retrospeaks.repository;

import com.champ.retrospeaks.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post,String>{
    List<Post> findAllByGroupId(int groupId);
    List<Post> findAllPostByUserID(Long userID);
}
