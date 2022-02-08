package com.example.projetappel.dao;

import com.example.projetappel.model.Absence;
import org.hibernate.Session;
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


//    public List getAbsCours(Integer etudiantId) {
//        String hql = " select ci.cours, count(*) from  Absence a, CoursInstance ci" +
//                     " where a.etudiant.id = :etudiantId " +
//                     " group by ci.cours.id";
//        List absencesCours = new ArrayList<>();
//        try (Session session = getSession()){
//            getTransaction(session);
//            Query query = session.createQuery(hql);
//            query.setParameter("etudiantId",etudiantId);
//            query.setParameter("coursId",coursId);
//            if (!query.getResultList().isEmpty()) {
//                absencesCours = query.getResultList();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return absencesCours;
//    }


}
