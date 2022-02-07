package com.example.projetappel.controller;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.EnseignantDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CompteController", value = "/compte")
public class CompteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur user= (Utilisateur) request.getAttribute("utilisateur");
        Integer userId = user.getId();

//        If (user instanceof Enseignant) {
//            EnseignantDao enseignantDao = new EnseignantDao();
//            ArrayList<Cours> coursEnseignant = (ArrayList<Cours>) enseignantDao.findCours(userId);
//            request.setAttribute("listCours", coursEnseignant);
//        } else if (user instanceof Etudiant) {
//            EtudiantDao etudiantDao = new EtudiantDao();
//            ArrayList<Cours> coursEtudiant = (ArrayList<Cours>) etudiantDao.findCours(userId);
//            request.setAttribute("listCours", coursEtudiant);
//        }

        request.setAttribute("page", "accueil");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

//    Utilisateur user= (Utilisateur) request.getAttribute("utilisateur");
//    Integer userId = user.getId();
//    AbsenceDao absenceDao= new AbsenceDao();
//    ArrayList<Absence> absences = (ArrayList<Absence>) absenceDao.getAbsences(userId);
//        request.setAttribute("listAbsences", absences);
//        request.setAttribute("page","consultation-etudiant");
//        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
//
//}
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//    }

}
