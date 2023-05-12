package com.springrestapi.user.exception;

public class UserCollectionException extends Exception{
    public UserCollectionException(String message) {

        super(message);
    }

    public static String NotFoundException(String id) {

        return " User with id(" + id + ") not found";
    }

    public static String UserAlreadyExists() {

        return "User with given name already exists";
    }
}
