package com.example.projetappel.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CoursInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Date date;

    private int duree;

    @ManyToOne
    private Cours cours;

    @ManyToOne
    private Utilisateur enseignant;

}
