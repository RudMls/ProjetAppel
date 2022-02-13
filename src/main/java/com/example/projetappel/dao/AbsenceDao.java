package com.example.projetappel.dao;

import com.example.projetappel.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AbsenceDao extends DAO<Absence> {
    public AbsenceDao() {
        super.setEntity(Absence.class);
    }

    public List<Absence> getAbsences(Integer etudiantId) {
            String hql = "select a from  Absence a where a.etudiant.id = :etudiantId";
        List<Absence> absences = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Absence> query = session.createQuery(hql);
            query.setParameter("etudiantId",etudiantId);
            if (!query.getResultList().isEmpty()) {
                absences = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absences;
    }
    public List<Absence> findByEtudiant(Integer etudiantId) {
        String hql = "select a from Absence a, FicheAppel f where a.ficheAppel.id = f.id and a.etudiant.id = :etudiantId and f.validee = true";
        List<Absence> absences = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Absence> query = session.createQuery(hql);
            query.setParameter("etudiantId",etudiantId);
            if (!query.getResultList().isEmpty()) {
                absences = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absences;
    }


    public List<Absence> getAbsCours(Integer etudiantId, Integer coursId) {
        String hql = " select a from  Absence a, FicheAppel fa, CoursInstance ci" +
                     " where a.ficheAppel.id = fa.id " +
                     " and fa.id = ci.ficheAppel.id " +
                     " and a.etudiant.id = :etudiantId " +
                     " and ci.cours.id = :coursId " ;
        List<Absence> absencesCours = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Absence> query = session.createQuery(hql);
            query.setParameter("etudiantId",etudiantId);
            query.setParameter("coursId",coursId);
            if (!query.getResultList().isEmpty()) {
                absencesCours = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absencesCours;
    }
    public List<Absence> getAbsTotCours(Integer ficheId) {
        String hql = " select a from  Absence a where a.ficheAppel.id = :ficheId ";

        List<Absence> absencesCours = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Absence> query = session.createQuery(hql);
            query.setParameter("ficheId",ficheId);
            if (!query.getResultList().isEmpty()) {
                absencesCours = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absencesCours;
    }
    public List<Absence> getAbsCoursAppelTermine(Integer coursId) {
        String hql = " select a from  Absence a, FicheAppel fa, CoursInstance ci" +
                " where a.ficheAppel.id = fa.id " +
                " and fa.id = ci.ficheAppel.id " +
                " and ci.cours.id = :coursId " +
                "and fa.validee=true";

        List<Absence> absencesCours = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Absence> query = session.createQuery(hql);
            query.setParameter("coursId",coursId);
            if (!query.getResultList().isEmpty()) {
                absencesCours = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absencesCours;
    }

    public List<Float> getAbsTotCoursMois(Integer coursId) {
        String hql = " select sum(count(a)/ (count(a) + count(p)))" +
                " from Absence a, FicheAppel fa, CoursInstance ci, Presence p" +
                " where a.ficheAppel.id = fa.id " +
                " and fa.id = ci.ficheAppel.id " +
                " and ci.cours.id = :coursId " +
                " and p.ficheAppel.id = fa.id" +
                " group by month(ci.dateDebut)" ;
        List<Float> absencesCours = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Float> query = session.createQuery(hql);
            query.setParameter("coursId",coursId);
            if (!query.getResultList().isEmpty()) {
                absencesCours = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absencesCours;
    }

    public void deleteByEtudiantFicheAppel(int etudiantPresent, int ficheAppelId) {
        String hql = "delete Absence a where a.etudiant.id = :etudiantId and a.ficheAppel.id=:ficheAppelId";
        try (Session session = getSession()){
            Transaction transaction=getTransaction(session);
            Query query = session.createQuery(hql);
            query.setParameter("etudiantId", etudiantPresent);
            query.setParameter("ficheAppelId", ficheAppelId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Absence findByEtudiantFicheAppel(int etudiantId, int ficheAppelId) {
        Absence absence = null;
        String hql = "select p from Absence p where p.etudiant.id = :etudiantId and p.ficheAppel.id = :ficheAppelId";
        try (Session session = getSession()){
            Transaction transaction=getTransaction(session);
            Query<Absence> query = session.createQuery(hql);
            query.setParameter("etudiantId", etudiantId);
            query.setParameter("ficheAppelId", ficheAppelId);
            if (!query.getResultList().isEmpty()) {
                absence = query.uniqueResult();
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absence;
    }



}
