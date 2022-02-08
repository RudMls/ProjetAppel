package com.example.projetappel.dao;

import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Presence;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PresenceDao extends DAO<Presence>{

    public PresenceDao() {
        super.setEntity(Presence.class);
    }

    public List<Presence> getPresences(Integer etudiantId) {
        String hql = "select p from  Presence p where p.etudiant.id = :etudiantId and p.retard = true ";
        List<Presence> presences = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Presence> query = session.createQuery(hql);
            query.setParameter("etudiantId",etudiantId);
            if (!query.getResultList().isEmpty()) {
                presences = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return presences;
    }


    public List<Presence> getPresCours(Integer etudiantId, Integer coursId) {
        String hql = " select p from  Presence p, FicheAppel fa, CoursInstance ci" +
                " where p.ficheAppel.id = fa.id " +
                " and fa.id = ci.ficheAppel.id " +
                " and p.etudiant.id = :etudiantId " +
                " and ci.cours.id = :coursId " +
                " and p.retard = true " ;
        List<Presence> presencesCours = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Presence> query = session.createQuery(hql);
            query.setParameter("etudiantId",etudiantId);
            query.setParameter("coursId",coursId);
            if (!query.getResultList().isEmpty()) {
                presencesCours = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return presencesCours;
    }

}
