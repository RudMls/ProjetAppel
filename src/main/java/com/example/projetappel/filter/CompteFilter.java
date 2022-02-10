package com.example.projetappel.filter;

import com.example.projetappel.dao.UtilisateurDao;
import com.example.projetappel.enumtype.Role;
import com.example.projetappel.model.Enseignant;
import com.example.projetappel.model.Etudiant;
import com.example.projetappel.model.Scolarite;
import com.example.projetappel.model.Utilisateur;
import com.example.projetappel.util.FileManager;

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
            request.setAttribute("utilisateur_image", getImageUrl(utilisateur));
            request.setAttribute("role", getRole(utilisateur));
            chain.doFilter(request, response);
        }
    }

    private String getImageUrl(Utilisateur utilisateur) {
        String imageUrl = "/files/default.png";
        if (utilisateur.getFichier() != null) {
            imageUrl = FileManager.getFichier(utilisateur.getFichier());
        }
        return imageUrl ;
    }

    private Role getRole(Utilisateur utilisateur) {
        Role role = null;
        if (utilisateur instanceof Enseignant) {
            role = Role.ENSEIGNANT;
        } else if (utilisateur instanceof Scolarite) {
            role = Role.SCOLARITE;
        } else if (utilisateur instanceof Etudiant) {
            role = Role.ETUDIANT;
        }
        return role;
    }
}
