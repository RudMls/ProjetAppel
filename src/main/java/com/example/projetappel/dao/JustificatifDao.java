package com.example.projetappel.dao;


import com.example.projetappel.model.Justificatif;
import org.hibernate.Session;

public class JustificatifDao extends DAO<Justificatif>{

    public JustificatifDao(){
        super.setEntity(Justificatif.class);}

}
