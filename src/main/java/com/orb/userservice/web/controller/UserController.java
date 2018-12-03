package com.orb.userservice.web.controller;

import com.orb.userservice.dao.UserDao;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "API pour les opérations CRUD sur les utilisateurs")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDao _userDao;


}
