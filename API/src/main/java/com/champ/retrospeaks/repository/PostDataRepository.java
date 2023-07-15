package com.champ.retrospeaks.repository;

import com.champ.retrospeaks.model.PostData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostDataRepository extends MongoRepository<PostData, String> {
}
