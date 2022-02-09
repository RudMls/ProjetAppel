package com.example.projetappel.controller;

import com.example.projetappel.dao.CoursInstanceDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.model.CoursInstance;
import com.example.projetappel.model.Etudiant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CoursInstanceController", value = "/compte/cours-instance")
public class CoursInstanceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            Integer coursIntanceId = stringToInteger(id);
            if (coursIntanceId != null) {
                CoursInstanceDao coursInstanceDao = new CoursInstanceDao();
                EtudiantDao etudiantDao = new EtudiantDao();
                ArrayList<Etudiant> listEtudiant =(ArrayList<Etudiant>) etudiantDao.getEtudiantCoursInstance(coursIntanceId);
                CoursInstance coursInstance = coursInstanceDao.find(coursIntanceId);
                request.setAttribute("coursInstance", coursInstance);
                request.setAttribute("listEtudiant", listEtudiant);
                request.setAttribute("page", "cours-instance");
                request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //List<Etudiant> etudiants= new ArrayList<>();
       String[] etudiants= request.getParameterValues("etudiantId");
       String presence;
        for (String etudiant:etudiants) {
            presence= request.getParameter(etudiant);
            System.out.println(presence);
        }
    }

    public Integer stringToInteger(String toParse) {
        Integer result;
        try {
            result = Integer.parseInt(toParse);
        } catch (NumberFormatException e) {
            result = null;
        }
        return result;
    }
}
