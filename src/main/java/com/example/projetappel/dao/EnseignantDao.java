package com.example.projetappel.dao;

import com.example.projetappel.model.Enseignant;

public class EnseignantDao extends DAO<Enseignant> {

    public EnseignantDao() {
        super.setEntity(Enseignant.class);
    }
}
