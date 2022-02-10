package com.example.projetappel.controller;

import com.example.projetappel.dao.AppartenirDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.model.Appartenir;
import com.example.projetappel.model.Etudiant;
import com.example.projetappel.model.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ListeEtudiantController", value = "/compte/liste-etudiant")
public class ListeEtudiantController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AppartenirDao appartenirDao = new AppartenirDao();
        EtudiantDao etudiantDao = new EtudiantDao();
        Etudiant etudiant = (Etudiant) request.getAttribute("utilisateur");
        ArrayList<Appartenir> listInscription = (ArrayList<Appartenir>) appartenirDao.findAll();
        request.setAttribute("listInscription", listInscription);

        String source = "";
        if (etudiant.getImageUrl() == null) {
            source = "/assets/compte/img/avatars/avatar1.jpeg";
        }else {
            source = etudiant.getImageUrl();
        }
        return source;

        String source = (String) etudiantDao.getUrlImg(etudiant);
        request.setAttribute("source", source);
        request.setAttribute("page","liste-etudiant");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
