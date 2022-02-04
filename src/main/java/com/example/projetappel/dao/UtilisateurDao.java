package com.example.projetappel.dao;

import com.example.projetappel.model.Utilisateur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UtilisateurDao extends DAO<Utilisateur> {

    public UtilisateurDao() {
        super.setEntity(Utilisateur.class);
    }

    public Utilisateur verifierUtilisatuer(String email, String pwd) {
        List<Utilisateur> utilisateurs = null;
        try (Session session = getSession()) {
            getTransaction(session);
            Query<Utilisateur> query = session.createQuery("select u " +
                    "from Utilisateur u " +
                    "where u.email =?1 " +
                    "and u.password =?2");
            query.setParameter(1, email);
            query.setParameter(2, pwd);
            utilisateurs = query.getResultList();
            System.out.println(utilisateurs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateurs.get(0);
    }
}
