package fr.epsi.orb.dao;

import fr.epsi.orb.model.Auth;

import java.util.List;

public interface AuthDAO {
    List<Auth> findAllAuth();
    Auth findById(String id);
    Auth save(Auth product);
}
