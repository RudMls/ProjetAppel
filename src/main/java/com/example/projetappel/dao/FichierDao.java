package com.example.projetappel.dao;

import com.example.projetappel.model.Fichier;

public class FichierDao extends DAO<Fichier> {

    public FichierDao () {
        super.setEntity(Fichier.class);
    }

}
