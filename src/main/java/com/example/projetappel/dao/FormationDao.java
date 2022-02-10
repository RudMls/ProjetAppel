package com.example.projetappel.dao;

import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Formation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class FormationDao extends DAO<Formation> {

    public FormationDao() {
        super.setEntity(Formation.class);
    }

    public List<Formation> getFormation() {
        String hql = "select f from  Formation f";
        List<Formation> formations = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Formation> query = session.createQuery(hql);
            if (!query.getResultList().isEmpty()) {
                formations = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formations;
    }
}
