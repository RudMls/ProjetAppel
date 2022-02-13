package com.example.projetappel.controller;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.CoursInstanceDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.dao.PresenceDao;
import com.example.projetappel.enumtype.Role;
import com.example.projetappel.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "ProfileEtudiantChercheController", value = "/compte/profile-etudiant-cherche")
public class ProfileEtudiantChercheController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String coursIdStr = request.getParameter("coursId");
        String etudiantIdStr = request.getParameter("etudiantId");
        EtudiantDao etudiantDao = new EtudiantDao();
        AbsenceDao absenceDao = new AbsenceDao();
        CoursInstanceDao coursInstanceDao = new CoursInstanceDao();

        if (etudiantIdStr != null && !etudiantIdStr.isEmpty()){

            int etudiantId = Integer.parseInt(etudiantIdStr);
            Etudiant etudiant = etudiantDao.find(etudiantId);

            //get nombre généralles d'absences par étudiant
            ArrayList<Absence> absences = (ArrayList<Absence>) absenceDao.getAbsences(etudiant.getId());
            float nbAbs = ((ArrayList<Absence>) absenceDao.getAbsences(etudiant.getId())).size();

            //get nombre général d'instances de cours où un étudiant était censé participer
            ArrayList<CoursInstance> coursInstances = (ArrayList<CoursInstance>) coursInstanceDao.getCoursInstances(etudiant.getId());
            float nbInstances = ((ArrayList<CoursInstance>) coursInstanceDao.getCoursInstances((etudiant.getId()))).size();

            //get nombre général de retards d'un étudiant donné
            PresenceDao presenceDao = new PresenceDao();
            float nbRetards = ((ArrayList<Presence>) presenceDao.getRetard(etudiant.getId())).size();

            //get taux général d'absences d'un étudiant
            float txAbsGen = (nbAbs / nbInstances) * 100;

            //get taux général de retards d'un étudiant
            float txRetGen = (nbRetards / nbInstances) * 100;

            request.setAttribute("etudiant", etudiant);
            request.setAttribute("txAbsGen", txAbsGen);
            request.setAttribute("txRetGen", txRetGen);

            if (coursIdStr != null && !coursIdStr.isEmpty()) {

                int coursId = Integer.parseInt(coursIdStr);
                //get nombre absences d'un étudiant pour un cours donné
                ArrayList<Absence> absCours = (ArrayList<Absence>) absenceDao.getAbsCours(etudiant.getId(), coursId);
                float nbAbsCours = ((ArrayList<Absence>) absenceDao.getAbsCours(etudiant.getId(), coursId)).size();

                //get nombre d'instances de cours où un étudiant donné était censé participer pour un cours donné
                ArrayList<CoursInstance> ciCours = (ArrayList<CoursInstance>) coursInstanceDao.getCiCours(coursId);
                float nbInstCours = ((ArrayList<CoursInstance>) coursInstanceDao.getCiCours(coursId)).size();

                //get nombre de retards d'un étudiant doné pour un cours donné
                float nbRetCours = ((ArrayList<Presence>) presenceDao.getPresCours(etudiant.getId(), coursId)).size();

                //get taux d'absences d'un étudiant par cours
                float txAbsCours = (nbAbsCours / nbInstCours) * 100;

                //get taux de retards d'un étudiant donné pour un cours donné
                float txRetCours = (nbRetCours / nbInstCours) * 100;

                request.setAttribute("txAbsCours", txAbsCours);
                request.setAttribute("txRetCours", txRetCours);
            }

        }

        request.setAttribute("page", "profile-etudiant-cherche");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

