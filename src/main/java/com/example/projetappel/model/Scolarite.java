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

    @OneToMany(
            mappedBy = "scolarite",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @MapKeyColumn (name = "justificatif_id", updatable = false, insertable = false)
    private Map<Justificatif, Notification> justificatifsNotification = new HashMap<>(0);

    public Scolarite() {}

    public Scolarite(String prenom, String nom, String email, String password) {
        super(prenom, nom, email, password);
    }

}
