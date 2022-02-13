package com.example.projetappel.controller;

import com.example.projetappel.dao.AppartenirDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.dao.FormationDao;
import com.example.projetappel.model.Etudiant;
import com.example.projetappel.model.Formation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FormationController", value = "/compte/formation")
public class FormationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FormationDao formationDao = new FormationDao();
        ArrayList<Formation> listFormation = (ArrayList<Formation>) formationDao.getFormation();
        request.setAttribute("listFormation", listFormation);
        request.setAttribute("page","formation");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
