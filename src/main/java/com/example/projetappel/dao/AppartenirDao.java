package com.example.projetappel.dao;

import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Appartenir;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AppartenirDao extends DAO<Appartenir>{

    public AppartenirDao() {
        super.setEntity(Appartenir.class);
    }

    public List<Appartenir> getInscriptions() {
        String hql = "select a from Appartenir a ";
            List<Appartenir> inscriptions = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Appartenir> query = session.createQuery(hql);
            if (!query.getResultList().isEmpty()) {
                inscriptions = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inscriptions;
    }
}

