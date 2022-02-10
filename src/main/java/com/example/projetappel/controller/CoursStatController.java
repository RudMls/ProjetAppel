package com.example.projetappel.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CoursStatController", value = "/compte/cours-statistiques")
public class CoursStatController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        AppartenirDao appartenirDao = new AppartenirDao();
//        ArrayList<Appartenir> listInscription = (ArrayList<Appartenir>) appartenirDao.getInscriptions();
//        request.setAttribute("listInscription", listInscription);

          request.setAttribute("page","cours-statistiques");
          request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
