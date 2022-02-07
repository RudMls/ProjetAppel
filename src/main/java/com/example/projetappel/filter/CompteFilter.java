package com.example.projetappel.filter;

import com.example.projetappel.dao.UtilisateurDao;
import com.example.projetappel.model.Enseignant;
import com.example.projetappel.model.Etudiant;
import com.example.projetappel.model.Scolarite;
import com.example.projetappel.model.Utilisateur;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CompteFilter", urlPatterns = "/compte/*")
public class CompteFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Integer userID = (Integer) ((HttpServletRequest) request).getSession().getAttribute("auth");
        if (userID == null) {
            ((HttpServletResponse) response).sendRedirect("/connexion");
        } else {
            UtilisateurDao utilisateurDao = new UtilisateurDao();
            Utilisateur utilisateur = utilisateurDao.find(userID);
            request.setAttribute("utilisateur", utilisateur);
            request.setAttribute("status", getStatus(utilisateur));
            chain.doFilter(request, response);
        }
    }

    private String getStatus(Utilisateur utilisateur) {
        String status = "";
        if (utilisateur instanceof Enseignant) {
            status = "Enseignant";
        }
        else if (utilisateur instanceof Scolarite) {
            status = "Scolarité";
        } else if (utilisateur instanceof Etudiant) {
            status = ((Etudiant) utilisateur).getTypeEtudiant().toString();
        }
        return status;
    }
}
