package com.example.projetappel.controller;

import com.example.projetappel.dao.JustificatifDao;
import com.example.projetappel.model.Justificatif;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ScolariteJustificatifController", value = "/scolarite-justificatif")
public class ScolariteJustificatifController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JustificatifDao justificatifDao = new JustificatifDao();
        ArrayList<Justificatif> justificatifs = (ArrayList<Justificatif>) justificatifDao.findAll();
        request.setAttribute("justificatifs", justificatifs);
        request.setAttribute("page", "scolarite-justificatif");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
