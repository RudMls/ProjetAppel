package com.example.projetappel.util;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.PresenceDao;
import com.example.projetappel.enumtype.AppelEtat;
import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Presence;

public class AppelManager {

    public static AbsenceDao absenceDao = new AbsenceDao();
    public static PresenceDao presenceDao = new PresenceDao();

    public static AppelEtat getAppelEtat(int etudiantId, int ficheAppelId) {
        AppelEtat appelEtat = AppelEtat.NON_NOTIFIE;
        Absence absence = absenceDao.findByEtudiantFicheAppel(etudiantId, ficheAppelId);
        Presence presence = presenceDao.findByEtudiantFicheAppel(etudiantId, ficheAppelId);
        if (absence != null) {
            appelEtat = AppelEtat.ABSENCE;
            if (absence.getJustificatif() != null && absence.getJustificatif().isValidee()) {
                appelEtat = AppelEtat.ABSENCE_JUSTIFIE;
            }
        } else if (presence != null) {
            appelEtat = AppelEtat.PRESENCE;
            if (presence.isRetard()) {
                appelEtat = AppelEtat.RETART;
            }
        }
        return appelEtat;
    }
}
