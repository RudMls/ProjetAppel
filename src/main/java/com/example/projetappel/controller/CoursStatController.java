package com.example.projetappel.controller;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Cours;
import com.example.projetappel.model.Etudiant;

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

        String cours = request.getParameter("coursId");
        int coursId = Integer.parseInt(cours);
        EtudiantDao etudiantDao = new EtudiantDao();
        AbsenceDao absenceDao = new AbsenceDao();
        ArrayList<Etudiant> listeEtudiantsAbsInj = (ArrayList<Etudiant>) etudiantDao.getEtudiantAbsInj(coursId);
        request.setAttribute("listeEtudiantsAbsInj", listeEtudiantsAbsInj);



        for (int i = 0; i < listeEtudiantsAbsInj.size(); i++) {
            ArrayList<Integer> listeNbAbsInj = (ArrayList<Integer>) etudiantDao.getNbAbsInj(coursId, listeEtudiantsAbsInj.get(i).getId());
        }

        float nbEtudPresCours = ((ArrayList<Etudiant>) etudiantDao.getEtudiantCours(coursId)).size();
        float nbEtudAbsCours = ((ArrayList<Absence>) absenceDao.getAbsTotCours(coursId)).size();
        float txAbsCours = (nbEtudAbsCours / (nbEtudPresCours + nbEtudAbsCours)) * 100 ;
        request.setAttribute("txAbsCours", txAbsCours);




        request.setAttribute("page","cours-statistiques");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
