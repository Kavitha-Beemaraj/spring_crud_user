package com.springrestapi.user.service;

import com.springrestapi.user.exception.UserCollectionException;
import com.springrestapi.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface UserService  {

    public void createUser(User user) throws ConstraintViolationException, UserCollectionException;

    public List<User> getAll();


    public User getSingleUser(String id) throws UserCollectionException;

    public void updateUser(String id, User user) throws UserCollectionException;

    public void deleteUserById(String id) throws UserCollectionException;


    List<User> getAllUsersSorted(String sortBy);

    List<User> findUsersByGender(String gender);

    List<User> findUsersBySubject(String subject);

    List<User> findUsersByAgeGreaterThan(int age);

    List<User> findUsersByAgeLessThan(int age);

    List<User> findUsersByAgeBetween(int maxAge, int minAge);


}
