package com.example.projetappel.dao;

import com.example.projetappel.model.Groupe;

public class GroupeDao extends DAO<Groupe> {

    public GroupeDao() {
        super.setEntity(Groupe.class);
    }

}
