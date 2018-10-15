package fr.epsi.orb.web.controller;

import fr.epsi.orb.dao.AuthDAO;
import fr.epsi.orb.model.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private AuthDAO authDAO;

    /**
     * Méthode de test
     * @return true
     */
    @RequestMapping(value="/connexion", method= RequestMethod.GET)
    public boolean connexion() {
        boolean res = 1 < 2 ? true : false;
        return res;
    }

    /**
     * Méthode pour retourner un utilisateur en fonction de son ID
     * @param id
     * @return l'utilisateur
     */
    @GetMapping(value = "/utilisateur/{id}")
    public Auth afficherUnUtilisateur(@PathVariable String id) {
        return authDAO.findById(id);
    }

    /**
     * Méthode pour retourner toutes la liste des utilisateurs
     * @return la liste des utilisateurs
     */
    @GetMapping(value = "/utilisateur")
    public List<Auth> afficherTousUtilisateur() {
        return authDAO.findAllAuth();
    }
}
