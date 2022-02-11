package com.example.projetappel.dao;

import com.example.projetappel.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public List<Presence> getRetCoursTot(Integer coursId) {
        String hql = " select p from  Presence p, FicheAppel fa, CoursInstance ci" +
                " where p.ficheAppel.id = fa.id " +
                " and fa.id = ci.ficheAppel.id " +
                " and ci.cours.id = :coursId " +
                " and p.retard = true " ;
        List<Presence> presencesCours = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Presence> query = session.createQuery(hql);
            query.setParameter("coursId",coursId);
            if (!query.getResultList().isEmpty()) {
                presencesCours = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return presencesCours;
    }

    public List<Presence> getPresCoursTot(Integer coursId) {
        String hql = " select p from  Presence p, FicheAppel fa, CoursInstance ci" +
                " where p.ficheAppel.id = fa.id " +
                " and fa.id = ci.ficheAppel.id " +
                " and ci.cours.id = :coursId " +
                " and p.retard = false " ;
        List<Presence> presencesCours = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Presence> query = session.createQuery(hql);
            query.setParameter("coursId",coursId);
            if (!query.getResultList().isEmpty()) {
                presencesCours = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return presencesCours;
    }

    public Presence findByEtudiantFicheAppel(int etudiantId, int ficheAppelId) {
        Presence presence = null;
        String hql = "select p from Presence p where p.etudiant.id = :etudiantId and p.ficheAppel.id = :ficheAppelId";
        try (Session session = getSession()){
            Transaction transaction=getTransaction(session);
            Query<Presence> query = session.createQuery(hql);
            query.setParameter("etudiantId", etudiantId);
            query.setParameter("ficheAppelId", ficheAppelId);
            if (!query.getResultList().isEmpty()) {
                presence = query.uniqueResult();
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return presence;
    }

    public void deleteByEtudiantFicheAppel(int etudiantPresent, int ficheAppelId) {
        String hql = "delete Presence p where p.etudiant.id = :etudiantId and p.ficheAppel.id = :ficheAppelId";
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

    public void setRetardCours(Etudiant etudiantPresent, FicheAppel ficheAppel) {
        Boolean retard =true;
        PresenceDao presenceDao = new PresenceDao();
        try (Session session = getSession()) {
            getTransaction(session);
            presenceDao.createOrUpdate(new Presence(etudiantPresent,ficheAppel,retard));
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


}
