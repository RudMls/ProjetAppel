package com.example.projetappel.controller;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.CoursInstanceDao;
import com.example.projetappel.dao.EtudiantDao;
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

            EtudiantDao etudiantDao = new EtudiantDao();

            AbsenceDao absenceDao = new AbsenceDao();
            ArrayList<Absence> absences = (ArrayList<Absence>) absenceDao.getAbsences(etudiant.getId());
            float nbAbs = ((ArrayList<Absence>) absenceDao.getAbsences(etudiant.getId())).size();

            CoursInstanceDao coursInstanceDao = new CoursInstanceDao();
            ArrayList<CoursInstance> coursInstances = (ArrayList<CoursInstance>) coursInstanceDao.getCoursInstances(etudiant.getId());
            float nbInstances = ((ArrayList<CoursInstance>) coursInstanceDao.getCoursInstances((etudiant.getId()))).size();

            float txAbsGen = (nbAbs / nbInstances) * 100;

            request.setAttribute("txAbsGen", txAbsGen);

        }

        request.setAttribute("page", "profile");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
