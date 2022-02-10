package com.example.projetappel.dao;

import com.example.projetappel.model.*;
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
    //Cette méthode renvoie la liste des cours pour un étudiant passé en paramètre
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
    //Cette méthode renvoie la liste des presences pour un étudiant passé en paramètre pour une instance de cours donné
    public List<Presence> getPresenceEtudiantCours (Etudiant etudiant, CoursInstance coursInstance) {
            String hql = " select p from  Presence p" +
                " where p.etudiant.id = :etudiantId " +
                " and p.ficheAppel.id = :ficheAppelId ";
        List<Presence> presenceEtudiant = new ArrayList<>();
        if(coursInstance.getFicheAppel()!=null){
            int ficheAppelCours= coursInstance.getFicheAppel().getId();
            try (Session session = getSession()) {
                getTransaction(session);
                Query<Presence> query = session.createQuery(hql);
                query.setParameter("etudiantId",etudiant.getId());
                query.setParameter("ficheAppelId",ficheAppelCours);
                if (!query.getResultList().isEmpty()) {
                    presenceEtudiant = query.getResultList();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return presenceEtudiant;
    }
    //Cette méthode renvoie la liste des absences pour un étudiant passé en paramètre pour un cours donné
    public List<Absence> getAbsenceEtudiantCours (Etudiant etudiant, CoursInstance coursInstance) {
        String hql = " select a from  Absence a" +
                " where a.etudiant.id = :etudiantId " +
                " and a.ficheAppel.id = :ficheAppelId ";
        List<Absence> absenceEtudiant = new ArrayList<>();
        if(coursInstance.getFicheAppel()!=null){
            int ficheAppelCours= coursInstance.getFicheAppel().getId();
            try (Session session = getSession()) {
                getTransaction(session);
                Query<Absence> query = session.createQuery(hql);
                query.setParameter("etudiantId",etudiant.getId());
                query.setParameter("ficheAppelId",ficheAppelCours);
                if (!query.getResultList().isEmpty()) {
                    absenceEtudiant = query.getResultList();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return absenceEtudiant;
    }

}

