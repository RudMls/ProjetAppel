package com.example.projetappel.dao;

import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Etudiant;
import com.example.projetappel.model.Utilisateur;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AbsenceDao extends DAO<Absence> {
    public AbsenceDao() {
        super.setEntity(Absence.class);
    }

    public ArrayList<Absence> getAbsences(Integer etudiantId) {
            String hql = "select e from Etudiant e, Absence a where a.etudiant = :etudiantId";
        List<Absence> absences = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<Utilisateur> query = session.createQuery(hql);
           

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<Absence>) absences;
    }
}
