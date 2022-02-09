package com.example.projetappel.controller;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.CoursInstanceDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.dao.PresenceDao;
import com.example.projetappel.enumtype.Role;
import com.example.projetappel.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProfileController", value = "/compte/profile")
public class ProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ((Role) request.getAttribute("role") == Role.ETUDIANT ) {
            Etudiant etudiant = (Etudiant) request.getAttribute("utilisateur");
            Cours cours = new Cours();

            EtudiantDao etudiantDao = new EtudiantDao();

            //get nombre généralles d'absences par étudiant
            AbsenceDao absenceDao = new AbsenceDao();
            ArrayList<Absence> absences = (ArrayList<Absence>) absenceDao.getAbsences(etudiant.getId());
            float nbAbs = ((ArrayList<Absence>) absenceDao.getAbsences(etudiant.getId())).size();

            //get nombre absences d'un étudiant pour un cours donné
            ArrayList<Absence> absCours = (ArrayList<Absence>) absenceDao.getAbsCours(etudiant.getId(), cours.getId());
            float nbAbsCours = ((ArrayList<Absence>) absenceDao.getAbsCours(etudiant.getId(), cours.getId())).size();

            //get nombre général d'instances de cours où un étudiant était censé participer
            CoursInstanceDao coursInstanceDao = new CoursInstanceDao();
            ArrayList<CoursInstance> coursInstances = (ArrayList<CoursInstance>) coursInstanceDao.getCoursInstances(etudiant.getId());
            float nbInstances = ((ArrayList<CoursInstance>) coursInstanceDao.getCoursInstances((etudiant.getId()))).size();

            //get nombre d'instances de cours où un étudiant donné était censé participer pour un cours donné
            ArrayList<CoursInstance> ciCours = (ArrayList<CoursInstance>) coursInstanceDao.getCiCours(etudiant.getId(), cours.getId());
            float nbInstCours = ((ArrayList<CoursInstance>) coursInstanceDao.getCiCours(etudiant.getId(), cours.getId())).size();

            //get nombre général de retards d'un étudiant donné
            PresenceDao presenceDao = new PresenceDao();
            float nbRetards = ((ArrayList<Presence>) presenceDao.getPresences(etudiant.getId())).size();

            //get nombre de retards d'un étudiant doné pour un cours donné
            float nbRetCours = ((ArrayList<Presence>) presenceDao.getPresCours(etudiant.getId(), cours.getId())).size();

            //get taux général d'absences d'un étudiant
            float txAbsGen = (nbAbs / nbInstances) * 100;
            //get taux d'absences d'un étudiant par cours
            float txAbsCours = (nbAbsCours / nbInstCours) * 100;
            //get taux général de retards d'un étudiant
            float txRetGen = (nbRetards / nbInstances) * 100;
            //get taux de retards d'un étudiant donné pour un cours donné
            float txRetCours = (nbRetCours / nbInstCours) * 100;

            request.setAttribute("txAbsGen", txAbsGen);
            request.setAttribute("txAbsCours", txAbsCours);
            request.setAttribute("txRetGen", txRetGen);
            request.setAttribute("txRetCours", txRetCours);


        }

        request.setAttribute("page", "profile");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
