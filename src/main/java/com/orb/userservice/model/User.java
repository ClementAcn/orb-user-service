package com.orb.userservice.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pseudo;

    private String mail;

    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp creation_date;


}
