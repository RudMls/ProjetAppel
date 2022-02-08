
package com.example.projetappel.dao;

import com.example.projetappel.model.Absence;
import com.example.projetappel.model.CoursInstance;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CoursInstanceDao extends DAO<CoursInstance> {

    public CoursInstanceDao() {
        super.setEntity(CoursInstance.class);
    }

    public List<CoursInstance> getCoursInstances(Integer etudiantId) {
        String hql = "select ci from  CoursInstance ci, Appartenir a, Groupe g " +
                " where a.etudiant.id = :etudiantId " +
                " and a.groupe.id = g.id " +
                " and g.id = ci.groupe.id " ;
        List<CoursInstance> coursInstances = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<CoursInstance> query = session.createQuery(hql);
            query.setParameter("etudiantId",etudiantId);
            if (!query.getResultList().isEmpty()) {
                coursInstances = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coursInstances;
    }

}
