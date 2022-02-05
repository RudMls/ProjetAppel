package com.example.projetappel.dao;

import com.example.projetappel.model.Etudiant;

public class EtudiantDao extends DAO<Etudiant> {

    public EtudiantDao() {
        super.setEntity(Etudiant.class);
    }
}
