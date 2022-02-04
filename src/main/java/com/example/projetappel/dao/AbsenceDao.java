package com.example.projetappel.dao;

import com.example.projetappel.model.Absence;

public class AbsenceDao extends DAO<Absence> {
    public AbsenceDao() {
        super.setEntity(Absence.class);
    }
}
