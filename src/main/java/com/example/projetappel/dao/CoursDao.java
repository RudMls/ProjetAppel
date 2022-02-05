package com.example.projetappel.dao;

import com.example.projetappel.model.Cours;

public class CoursDao extends DAO<Cours> {

    public CoursDao() {
        super.setEntity(Cours.class);
    }

}
