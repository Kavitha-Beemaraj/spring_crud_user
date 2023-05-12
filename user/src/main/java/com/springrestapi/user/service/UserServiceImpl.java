package com.springrestapi.user.service;

import com.springrestapi.user.exception.UserCollectionException;
import com.springrestapi.user.model.User;
import com.springrestapi.user.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    private final MongoTemplate mongoTemplate;


    @Autowired
    private UserRespository userRepo;

    public UserServiceImpl(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void createUser(User user) throws ConstraintViolationException, UserCollectionException {
        Optional<User> userOptional = userRepo.findByName(user.getName());
        if (userOptional.isPresent()) {
            throw new UserCollectionException(UserCollectionException.UserAlreadyExists());
        } else {
            userRepo.save(user);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepo.findAll();
        if (users.size() > 0) {
            return users;
        } else {
            return new ArrayList<User>();
        }
    }


    @Override
    public User getSingleUser(String id) throws UserCollectionException {
        Optional<User> userOptional = userRepo.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserCollectionException(UserCollectionException.NotFoundException(id));
        } else {
            return userOptional.get();
        }
    }

    @Override
    public void updateUser(String id, User user) throws UserCollectionException {
        Optional<User> userOptional = userRepo.findById(id);
        Optional<User> userWithSameName = userRepo.findByName(user.getName());
        if (userOptional.isPresent()) {

            if (userWithSameName.isPresent() && !userWithSameName.get().getId().equals(id)) {
                throw new UserCollectionException(UserCollectionException.UserAlreadyExists());
            }
            user.setId(userOptional.get().getId());
            userRepo.save(user);
        } else {
            throw new UserCollectionException(UserCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteUserById(String id) throws UserCollectionException {
        Optional<User> userOptional = userRepo.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserCollectionException(UserCollectionException.NotFoundException(id));
        } else {
            userRepo.deleteById(id);
        }
    }

    @Override
    public List<User> getAllUsersSorted(String sortBy) {
        Sort users = Sort.by(Sort.Direction.DESC, sortBy);
        return userRepo.findAll(users);
    }

    @Override
    public List<User> findUsersByGender(String gender) {
        return userRepo.findByGender(gender);
    }

    @Override
    public List<User> findUsersBySubject(String subject) {
        return userRepo.findBySubject(subject);
    }

    @Override
    public List<User> findUsersByAgeGreaterThan(int age) {
        Query query = new Query();
        query.addCriteria(Criteria.where("age").gte(age));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public List<User> findUsersByAgeLessThan(int age) {
        Query query = new Query();
        query.addCriteria(Criteria.where("age").lte(age));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public List<User> findUsersByAgeBetween(int maxAge, int minAge) {
        Query query = new Query();
        query.addCriteria(Criteria.where("age").gte(minAge).lte(maxAge));
        return mongoTemplate.find(query, User.class);
    }


}
