package com.springrestapi.user.repository;

import com.springrestapi.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRespository extends MongoRepository<User, String> {
    @Query("{'name':?0}")
    Optional<User> findByName(String name);

    List<User> findByGender(String gender);

    List<User> findBySubject(String subject);
}
