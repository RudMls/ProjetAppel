package com.example.projetappel.controller;

import com.example.projetappel.dao.UtilisateurDao;
import com.example.projetappel.model.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "ConnexionController", value = "/connexion")
public class ConnexionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/connexion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, String> erreurs = new HashMap<>();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty()) {
            erreurs.put("email_erreur", "Veillez entrer l'addresse email");
        }
        if (password == null || password.isEmpty()) {
            erreurs.put("password_erreur", "Veillez entrer un mot de passe");
        }

        if (erreurs.isEmpty()) {
            UtilisateurDao utilisateurDao = new UtilisateurDao();
            Utilisateur utilisateur = utilisateurDao.loginUtilisateur(email, password);

        } else {
            erreurs.forEach(request::setAttribute);
            request.getRequestDispatcher("view/connexion.jsp").forward(request, response);
        }
    }

}
