package com.example.projetappel.dao;

import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Cours;
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

    public List<Cours> findCours (Integer etudiantId) {
        String hql = " select c from  Cours c, CoursInstance ci, Appartenir a, Groupe g" +
                     " where a.etudiant.id = :Id " +
                     " and a.groupe.id = g.id " +
                     " and g.id = ci.groupe.id " +
                     " and ci.cours.id = c.id ";
        List<Cours> coursEtudiant = new ArrayList<>();
        try (Session session = getSession()) {
            getTransaction(session);
            Query<Cours> query = session.createQuery(hql);
            query.setParameter("Id",etudiantId);
                if (!query.getResultList().isEmpty()) {
                    coursEtudiant = query.getResultList();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return coursEtudiant;
        }


}

