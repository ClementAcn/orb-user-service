package com.orb.userservice.web.controller;

import com.orb.userservice.dao.UserDao;
import com.orb.userservice.model.User;
import com.orb.userservice.web.exceptions.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "API pour les opérations CRUD sur les utilisateurs")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDao _userDao;

    @ApiOperation(value = "Recupere la liste de tous les utilisateurs")
    @GetMapping(value = "/")
    public List<User> getAll(){
        return _userDao.findAll();
    }

    @ApiOperation(value = "Recupere un utilisateur en fonction de son ID")
    @GetMapping(value = "/{id}")
    public User getById(@PathVariable("id") int id) throws UserNotFoundException {
        User user = _userDao.findById(id);

        if(user == null)
            throw new UserNotFoundException("Utilisateur inexistant");

        return _userDao.findById(id);
    }
}
