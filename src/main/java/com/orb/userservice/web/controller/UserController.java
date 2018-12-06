package com.orb.userservice.web.controller;

import com.orb.userservice.dao.UserDao;
import com.orb.userservice.model.User;
import com.orb.userservice.web.exceptions.UserAlreadyExists;
import com.orb.userservice.web.exceptions.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @ApiOperation(value = "Recupere un utilisateur en fonction de son pseudo")
    @GetMapping(value = "/findByPseudo")
    public User getByPseudo(@RequestParam("pseudo") String pseudo){
        return _userDao.findByPseudo(pseudo);
    }

    @ApiOperation(value = "Recupere un utilisateur en fonction de son mail")
    @GetMapping(value = "/findByMail")
    public User getByMail(@RequestParam("mail") String mail){
        return _userDao.findByMail(mail);
    }

    @ApiOperation(value = "Ajout d'un utilisateur")
    @PostMapping(value = "/")
    public ResponseEntity<User> create(@RequestBody User user) throws UserAlreadyExists {

        if(_userDao.findByMail(user.getMail()) != null)
            throw new UserAlreadyExists("L'adresse mail est déjà utilisée pour un autre compte.");
        if(_userDao.findByPseudo(user.getPseudo()) != null)
            throw new UserAlreadyExists("Ce pseudo existe déjà pour un autre compte");
        
        User userAdded = _userDao.save(user);

        if(userAdded == null)
            return ResponseEntity.noContent().build();

        URI loc = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userAdded.getId())
                .toUri();

        return ResponseEntity.created(loc).build();
    }
}
