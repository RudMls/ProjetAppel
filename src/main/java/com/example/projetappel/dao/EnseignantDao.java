package com.example.projetappel.dao;

import com.example.projetappel.model.Cours;
import com.example.projetappel.model.Enseignant;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EnseignantDao extends DAO<Enseignant> {

    public EnseignantDao() {
        super.setEntity(Enseignant.class);
    }

    public List<Cours> findCours (Integer enseignantId) {
        String hql = "select c from  Cours c, CoursInstance ci" +
                     " where ci.enseignant.id = :Id ";

        List<Cours> coursEnseignant = new ArrayList<>();
        try (Session session = getSession()) {
            getTransaction(session);
            Query<Cours> query = session.createQuery(hql);
            query.setParameter("Id",enseignantId);
            if (!query.getResultList().isEmpty()) {
                coursEnseignant = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coursEnseignant;
    }

}
