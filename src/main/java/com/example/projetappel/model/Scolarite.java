package com.example.projetappel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Scolarite extends Utilisateur implements Serializable {

    @OneToMany(
            mappedBy = "scolarite",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Justificatif> justificatifs = new HashSet<>();

    public Scolarite() {}

    public Scolarite(String prenom, String nom, String email, String password) {
        super(prenom, nom, email, password);
    }

}
