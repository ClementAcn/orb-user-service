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
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/user")
class UserController {

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


    @ApiOperation(value = "Crée un utilisateur, utilisé lors de l'inscription")
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

    @ApiOperation(value = "Mise à jour d'un utilisateur")
    @PutMapping(value = "/")
    public User update(@RequestBody User user){
        return _userDao.save(user);
    }

    @ApiOperation(value = "Suppression d'un utilisateur")
    @DeleteMapping(value = "/")
    public void delete(@RequestBody User user){
        _userDao.delete(user);
    }


    @ApiOperation(value = "Connexion d'un utilisateur")
    @PostMapping(value = "/login")
    public User login(@RequestParam String pseudo, @RequestParam String password) throws UserNotFoundException {
        User user = getByPseudo(pseudo);
        if(user == null)
            throw new UserNotFoundException("Utilisateur non existant");
        if(!user.getPassword().equals(password))
            return null;
        return user;
    }
}
