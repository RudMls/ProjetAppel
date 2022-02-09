package com.example.projetappel.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn
public class Enseignant extends Utilisateur implements Serializable {

    @OneToMany(mappedBy = "enseignant", fetch = FetchType.EAGER)
    private Set<CoursInstance> coursInstances = new HashSet<>();

    public Enseignant() {}

    public Enseignant(String prenom, String nom, String email, String password) {
        super(prenom, nom, email, password);
    }

    public Set<CoursInstance> getCoursInstances() {
        return coursInstances;
    }

    public void setCoursInstances(Set<CoursInstance> coursInstances) {
        this.coursInstances = coursInstances;
    }


}
