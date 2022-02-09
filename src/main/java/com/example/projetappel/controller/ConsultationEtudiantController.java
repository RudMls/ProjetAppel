package com.example.projetappel.controller;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.dao.UtilisateurDao;
import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Etudiant;
import com.example.projetappel.model.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "ConsultationEtudiantController", value = "/compte/consultation-etudiant")
public class ConsultationEtudiantController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur user= (Utilisateur) request.getAttribute("utilisateur");
        Integer userId = user.getId();
        AbsenceDao absenceDao= new AbsenceDao();
        ArrayList<Absence> absences = (ArrayList<Absence>) absenceDao.getAbsences(userId);
        request.setAttribute("listAbsences", absences);
        request.setAttribute("page","consultation-etudiant");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] absencesIds = request.getParameterValues("abscence_ids");
        if (absencesIds == null || absencesIds.length == 0) {
            doGet(request, response);
        } else {
            AbsenceDao absenceDao = new AbsenceDao();
            ArrayList<Absence> absences = new ArrayList<>();
            Arrays.asList(absencesIds).forEach(s -> {
                absences.add(absenceDao.find(Integer.parseInt(s)));
            });
            request.getSession().setAttribute("absences", absences);
            response.sendRedirect("/compte/justificatif");
        }

    }
}
