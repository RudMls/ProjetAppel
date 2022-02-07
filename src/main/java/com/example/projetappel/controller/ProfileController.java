package com.example.projetappel.controller;

import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.enumtype.Role;
import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Cours;
import com.example.projetappel.model.Etudiant;
import com.example.projetappel.model.Utilisateur;

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
            //ArrayList<Absence> absences = (ArrayList<Absence>) etudiantDao.
            ArrayList<Cours> cours = (ArrayList<Cours>) etudiantDao.findCours(etudiant.getId());
        }

        request.setAttribute("page", "profile");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
