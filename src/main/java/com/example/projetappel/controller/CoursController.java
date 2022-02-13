package com.example.projetappel.controller;

import com.example.projetappel.dao.AppartenirDao;
import com.example.projetappel.dao.CoursDao;
import com.example.projetappel.dao.EnseignantDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.model.Appartenir;
import com.example.projetappel.model.Cours;
import com.example.projetappel.model.Enseignant;
import com.example.projetappel.model.Etudiant;
import com.github.javafaker.App;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CoursController", value = "/compte/cours")
public class CoursController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enseignant enseignant = (Enseignant) request.getAttribute("utilisateur");
        EnseignantDao enseignantDao = new EnseignantDao();


        CoursDao coursDao = new CoursDao();
        ArrayList<Cours> listeCours = (ArrayList<Cours>) coursDao.getCours(enseignant.getId());
        request.setAttribute("listeCours", listeCours);

        request.setAttribute("page", "cours");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
