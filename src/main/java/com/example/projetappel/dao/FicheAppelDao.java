package com.example.projetappel.dao;

import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Etudiant;
import com.example.projetappel.model.FicheAppel;
import com.example.projetappel.model.Presence;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class  FicheAppelDao extends DAO<FicheAppel> {

    public FicheAppelDao() {
        super.setEntity(FicheAppel.class);
    }

    public void setValiderTrue(FicheAppel ficheAppel) {
        FicheAppelDao ficheAppelDao = new FicheAppelDao();
        try (Session session = getSession()) {
            getTransaction(session);
            ficheAppel.setValidee(true);
            ficheAppelDao.update(ficheAppel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<FicheAppel> getFicheAppelAbsence(Absence absence) {
        String hql = "select f from  FicheAppel f where f.absences = :absence";
        List<FicheAppel> ficheAppelsAbsences = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<FicheAppel> query = session.createQuery(hql);
            query.setParameter("absence",absence);
            if (!query.getResultList().isEmpty()) {
                ficheAppelsAbsences = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ficheAppelsAbsences;
    }

    public List<FicheAppel> getFicheAppelPresence(Presence presence) {
        String hql = "select f from  FicheAppel f where f.presences = :presence";
        List<FicheAppel> ficheAppelsPresent = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<FicheAppel> query = session.createQuery(hql);
            query.setParameter("presence",presence);
            if (!query.getResultList().isEmpty()) {
                ficheAppelsPresent = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ficheAppelsPresent;
    }
}
