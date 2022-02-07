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



//        try (Session session = getSession()) {
//            getTransaction(session);
//            Query query = session.createQuery("select c.libelle " +
//                    "from Etudiant e, Appartenir a, Groupe g, CoursInstance ci, Cours c " +
//                    "where e.id = a.etudiant.id " +
//                    "and a.groupe.id = g.id " +
//                    "and g.id = ci.groupe.id " +
//                    "and ci.cours.id = c.id " +
//                    "and e.id = " + etudiant.getId());
//            query.list().forEach(System.out::println);
//            query.list().forEach(nbCours += 1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cours;

}

