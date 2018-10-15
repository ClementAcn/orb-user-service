package fr.epsi.orb.dao;

import fr.epsi.orb.model.Auth;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthDAOImp implements AuthDAO {

    //Var test pendant la non bdd
    public static List<Auth> auths=new ArrayList<>();
    static {
        auths.add(new Auth(new String("C3DRIC"), "mart8"));
        auths.add(new Auth(new String("SamixPDG"), "chocolatinemaster"));
        auths.add(new Auth(new String("Clemster44Lamasse"), "bgdu44teamCHOCOLATINE"));
    }

    @Override
    public List<Auth> findAllAuth() {
        return auths;
    }

    @Override
    public Auth findById(String id) {
        for (Auth auth : auths) {
            if(auth.getIdentifiant().equals(id)){
                return auth;
            }
        }
        return null;
    }

    @Override
    public Auth save(Auth auth) {
        auths.add(auth);
        return auth;
    }
}
