package com.example.projetappel.controller;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.CoursDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.dao.PresenceDao;
import com.example.projetappel.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "CoursStatController", value = "/compte/cours-statistiques")
public class CoursStatController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CoursDao coursDao = new CoursDao();
        String coursIdStr = request.getParameter("coursId");
        int coursId = Integer.parseInt(coursIdStr);
        Cours cours = coursDao.find(coursId);

        PresenceDao presenceDao = new PresenceDao();
        EtudiantDao etudiantDao = new EtudiantDao();
        AbsenceDao absenceDao = new AbsenceDao();

        ArrayList<Etudiant> listeEtudiantsAbsInj = (ArrayList<Etudiant>) etudiantDao.getEtudiantAbsInj(coursId);
        request.setAttribute("listeEtudiantsAbsInj", listeEtudiantsAbsInj);
        for (int i = 0; i < listeEtudiantsAbsInj.size(); i++) {
            ArrayList<Integer> listeNbAbsInj = (ArrayList<Integer>) etudiantDao.getNbAbsInj(coursId, listeEtudiantsAbsInj.get(i).getId());
        }

        //get nombre de retards d'un étudiant doné pour un cours donné
        float nbEtudPresCours = ((ArrayList<Etudiant>) etudiantDao.getEtudiantCours(coursId)).size();
        float nbEtudAbsCours = ((ArrayList<Absence>) absenceDao.getAbsTotCours(coursId)).size();
        float txAbsCours = (nbEtudAbsCours / (nbEtudPresCours + nbEtudAbsCours)) * 100 ;
        request.setAttribute("txAbsCours", txAbsCours);

//                ArrayList<CoursInstance> coursInstanceFiltre = coursInstances.stream()
//                        .filter(coursInstance -> datePlanning.isWithinRange(date, firstDayOfWeek, lastDayOfWeek))
//                        .collect(Collectors.toCollection(ArrayList::new));
//                responseJSON.setBody(coursInstancesResponse);
//                json = gson.toJson(responseJSON);

        HashMap<Etudiant, Integer> nombreAbsencesParEtudiant = new HashMap<>();

        ArrayList<Etudiant> etudiants = new ArrayList<>(etudiantDao.findAll());
;
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getAbsences().size() > 1 ) {
                nombreAbsencesParEtudiant.put(etudiant, etudiant.getAbsences().size());
            }
        }
        float nbRetCours = ((ArrayList<Presence>) presenceDao.getRetCoursTot(cours.getId())).size();
        float nbPresCours = ((ArrayList<Presence>) presenceDao.getPresCoursTot(cours.getId())).size();

        List<Float> getAbsTotCoursMois = ((List<Float>) absenceDao.getAbsTotCoursMois(coursId));
        request.setAttribute("getAbsTotCoursMois", getAbsTotCoursMois);

        request.setAttribute("nbRetCours", nbRetCours);
        request.setAttribute("nbPresCours", nbPresCours);
        request.setAttribute("nbEtudAbsCours", nbPresCours);
        request.setAttribute("nombreAbsencesParEtudiant", nombreAbsencesParEtudiant);
        request.setAttribute("cours", cours);
        request.setAttribute("page","cours-statistiques");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
