package com.example.projetappel.dao;

import com.example.projetappel.model.Enseignant;

public class EnseignantDao extends DAO<Enseignant> {

    public EnseignantDao() {
        super.setEntity(Enseignant.class);
    }

    public static void main(String[] args) {

        Enseignant enseignant = new Enseignant("Nathalie", "Valles");

        EnseignantDao enseignantDao = new EnseignantDao();
        enseignantDao.create(enseignant);


    }

}
