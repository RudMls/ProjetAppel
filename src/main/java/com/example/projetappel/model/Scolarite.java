package com.example.projetappel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "scolarite_id")
public class Scolarite extends Utilisateur implements Serializable {

    public Scolarite() {}

    public Scolarite(String prenom, String nom, String email, String password) {
        super(prenom, nom, email, password);
    }

}
