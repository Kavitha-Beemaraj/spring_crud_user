package com.springrestapi.user.controller;

import com.springrestapi.user.exception.UserCollectionException;
import com.springrestapi.user.model.User;
import com.springrestapi.user.repository.UserRespository;
import com.springrestapi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRespository userRepo;
    @Autowired
    private UserService userService;

    //read all
    @GetMapping("/users")
    public ResponseEntity<?> getAll() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // create
    @PostMapping(value = "/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //read by id
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getSingleUser(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(userService.getSingleUser(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //update
    @PatchMapping("/users/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody User user) {
        try {
            userService.updateUser(id, user);
            return new ResponseEntity<>("Updated user todo with id  (" + id + ") is successful", HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // delete by id
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>("Deleted user successfully with id " + id, HttpStatus.OK);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //sort
    @RequestMapping("/users/sort")
    public ResponseEntity<?> getAllSorted(@RequestParam String sortBy) {
        try {
            List<User> users = userService.getAllUsersSorted(sortBy);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //filter gender
    @GetMapping("/users/filter/{gender}")
    public ResponseEntity<?> getByGender(@PathVariable("gender") String gender) {
        try {
            List<User> users = userService.findUsersByGender(gender);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //filter subject
    @GetMapping("/users/filterSubject/{subject}")
    public ResponseEntity<?> getBySubject(@PathVariable("subject") String subject) {
        try {
            List<User> users = userService.findUsersBySubject(subject);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // filter age greater than
    @GetMapping("/users/filterAgeGreaterThan/{age}")
    public ResponseEntity<?> getAgeGreater(@PathVariable("age") int age) {
        try {
            List<User> users = userService.findUsersByAgeGreaterThan(age);
            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //filter age less than
    @GetMapping("/users/filterAgeLessThan/{age}")
    public ResponseEntity<?> getAgeLesser(@PathVariable("age") int age) {
        try {
            List<User> users = userService.findUsersByAgeLessThan(age);
            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //filter age in between
    @GetMapping("/users/filterAgeBetween/{maxAge}/{minAge}")
    public ResponseEntity<?> getAgeBetween(@PathVariable("maxAge") int maxAge, @PathVariable("minAge") int minAge) {
        try {
            List<User> users = userService.findUsersByAgeBetween(maxAge, minAge);
            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
