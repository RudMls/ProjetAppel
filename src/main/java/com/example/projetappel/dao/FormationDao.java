package com.example.projetappel.dao;

import com.example.projetappel.model.Formation;

public class FormationDao extends DAO<Formation> {

    public FormationDao() {
        super.setEntity(Formation.class);
    }
}
