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

    public Utilisateur loginUtilisateur(String email, String pwd) {
        Utilisateur utilisateurs = null;
        try (Session session = getSession()) {
            getTransaction(session);
            Query<Utilisateur> query = session.createQuery("select u " +
                    "from Utilisateur u " +
                    "where u.email = :email " +
                    "and u.password = :password");
            query.setParameter("email", email);
            query.setParameter("password", pwd);
            if (query.getResultList().size() != 0) {
                utilisateurs = query.getResultList().get(0);
            }
            System.out.println(utilisateurs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public boolean emailExiste(String email) {
        String hql = "select u from Utilisateur u where u.email = :email";
        try (Session session = getSession()){
            getTransaction(session);
            Query<Utilisateur> query = session.createQuery(hql, Utilisateur.class);
            query.setParameter("email", email);
            if (query.getResultList().size() != 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
