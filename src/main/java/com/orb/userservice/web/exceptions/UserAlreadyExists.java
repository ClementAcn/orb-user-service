package com.orb.userservice.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExists extends Exception {

    public UserAlreadyExists(String s){
        super(s);
    }
}
