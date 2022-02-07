package com.example.projetappel.dao;

import com.example.projetappel.model.Etudiant;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EtudiantDao extends DAO<Etudiant> {

    public EtudiantDao() {
        super.setEntity(Etudiant.class);
    }

    public List<Etudiant> getEtudiantCoursInstance(int coursInstanceId){
        String hql = "select e" +
                " from Appartenir a, Cours c, CoursInstance i, Etudiant e " +
                " where i.id = :coursInstanceId" +
                " and i.cours.id = c.id" +
                " and c.formation.id = a.formation.id" +
                " and i.groupe.id = a.groupe.id" +
                " and a.etudiant.id = e.id";
        List<Etudiant> etudiants = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Etudiant> query = session.createQuery(hql);
            query.setParameter("coursInstanceId", coursInstanceId);
            if (!query.getResultList().isEmpty()) {
                etudiants = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }
}
