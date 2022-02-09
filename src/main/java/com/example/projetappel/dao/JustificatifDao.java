package com.example.projetappel.dao;

import com.example.projetappel.model.Justificatif;

public class JustificatifDao extends DAO<Justificatif>{

    public JustificatifDao(){
        super.setEntity(Justificatif.class);
    }
}
