package com.example.projetappel.dao;

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
}
