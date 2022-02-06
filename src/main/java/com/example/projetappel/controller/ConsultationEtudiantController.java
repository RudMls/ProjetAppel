package com.example.projetappel.controller;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.dao.UtilisateurDao;
import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Etudiant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ConsultationEtudiantController", value = "/consultationEtudiant")
public class ConsultationEtudiantController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/consultationEtudiant.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String etudiant = req.getParameter("etudiant");
        AbsenceDao absenceDao = new AbsenceDao();

    }
}
