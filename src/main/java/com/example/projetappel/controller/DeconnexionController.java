package com.example.projetappel.controller;

import com.example.projetappel.model.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeconnexionController", value = "/deconnexion")
public class DeconnexionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("auth")!= null) {
            request.getSession().removeAttribute("auth");
        }
        response.sendRedirect("connexion");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
