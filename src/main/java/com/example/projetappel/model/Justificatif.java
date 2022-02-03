package com.example.projetappel.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Justificatif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String texte;

    private Date date;

    private boolean validee;

}
