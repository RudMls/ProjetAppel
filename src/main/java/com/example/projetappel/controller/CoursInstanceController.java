package com.example.projetappel.controller;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.CoursDao;
import com.example.projetappel.dao.CoursInstanceDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Etudiant;
import com.example.projetappel.model.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CoursInstanceController", value = "/cours-instance")
public class CoursInstanceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer coursInstanceId = (Integer) request.getAttribute("coursInstanceId");
        EtudiantDao etudiantDao = new EtudiantDao();
        ArrayList<Etudiant> listEtudiant = (ArrayList<Etudiant>) etudiantDao.getEtudiantCoursInstance(coursInstanceId);
        request.setAttribute("listEtudiant", listEtudiant);
        request.getRequestDispatcher("/view/compte/fiche-appel.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
