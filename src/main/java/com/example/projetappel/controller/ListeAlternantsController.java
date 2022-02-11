package com.example.projetappel.controller;

import com.example.projetappel.dao.*;
import com.example.projetappel.model.*;
import com.example.projetappel.util.DatePlanning;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.projetappel.util.FakeData.SDF;

@WebServlet(name = "ListeAlternantsController", value = "/compte/liste-alternants")
public class ListeAlternantsController extends HttpServlet {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat SDF_OLD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SDF_NEW_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int formationId = Integer.parseInt(request.getParameter("formationId"));
        FormationDao formationDao = new FormationDao();
        EtudiantDao etudiantDao = new EtudiantDao();
        Formation formation = formationDao.find(formationId);
        ArrayList<Etudiant> listeAlternants = (ArrayList<Etudiant>) etudiantDao.getEtudiantAlternt(formation);

        AbsenceDao absenceDao = new AbsenceDao();
        PresenceDao presenceDao = new PresenceDao();
        FicheAppelDao ficheAppelDao = new FicheAppelDao();
        CoursInstanceDao coursInstanceDao = new CoursInstanceDao();
        int nbAbsences = 0;
        int nbPresences = 0;
        Date date = new Date();
        SDF.format(date);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(SDF.parse(SDF.format(date)));
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            Date firstDayOfWeek = calendar.getTime();
            for (int i = 2; i <= 7; i++) {
                calendar.add(Calendar.DATE, 1);
            }
            Date lastDayOfWeek = calendar.getTime();
            for (Etudiant etudiants : listeAlternants) {
                Etudiant etudiantCherche = etudiantDao.find(etudiants.getId());
                int etudiantId = etudiantCherche.getId();
                //trouver absences
                ArrayList<Absence> listeAbsence = (ArrayList<Absence>) absenceDao.getAbsences(etudiantId);
                for (Absence absence : listeAbsence) {
                    Date dateCoursInstanceAbsence = absence.getFicheAppel().getCoursInstance().getDateDebut();
                    if (dateCoursInstanceAbsence != null) {
                        if (DatePlanning.isWithinRange(dateCoursInstanceAbsence, firstDayOfWeek, lastDayOfWeek)) {
                            nbAbsences++;
                        }
                    }
                }
                //trouver presences
                ArrayList<Presence> listePresence = (ArrayList<Presence>) presenceDao.getPresences(etudiantId);
                for (Presence presence : listePresence) {
                    Date dateCoursInstancePresence = presence.getFicheAppel().getCoursInstance().getDateDebut();
                    if (dateCoursInstancePresence != null) {
                        if (DatePlanning.isWithinRange(dateCoursInstancePresence, firstDayOfWeek, lastDayOfWeek)) {
                            nbPresences++;
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        request.setAttribute("nbAbsences", nbAbsences);
        request.setAttribute("nbPresences", nbPresences);
        request.setAttribute("listeAlternants", listeAlternants);
        request.setAttribute("page", "liste-alternants");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }
}
