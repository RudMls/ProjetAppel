package com.example.projetappel.dao;

import com.example.projetappel.model.Utilisateur;

public class UtilisateurDao extends DAO<Utilisateur> {

    public UtilisateurDao() {
        super.setEntity(Utilisateur.class);
    }

}
