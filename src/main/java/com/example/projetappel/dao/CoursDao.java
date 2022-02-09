package com.example.projetappel.dao;

import com.example.projetappel.model.Cours;
import com.example.projetappel.model.Presence;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CoursDao extends DAO<Cours> {

    public CoursDao() {
        super.setEntity(Cours.class);
    }

    public List<Cours> getCours(Integer enseignantId) {
        String hql = " select distinct c from  Cours c, CoursInstance ci " +
                     " where ci.enseignant.id = :enseignantId " +
                     " and ci.cours.id = c.id ";
        List<Cours> cours = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Cours> query = session.createQuery(hql);
            query.setParameter("enseignantId", enseignantId);
            if (!query.getResultList().isEmpty()) {
                cours = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cours;
    }
}
