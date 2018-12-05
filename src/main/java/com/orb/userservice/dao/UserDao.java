package com.orb.userservice.dao;

import com.orb.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findAll();

    User findById(int id);

    @Query(value = "SELECT * from User s where s.pseudo = :pseudo", nativeQuery = true)
    User findByPseudo(@Param("pseudo") String pseudo);
}
