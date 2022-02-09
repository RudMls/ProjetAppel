package com.example.projetappel.controller;

import com.example.projetappel.dao.*;
import com.example.projetappel.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
        EtudiantDao etudiantDao = new EtudiantDao();
        PresenceDao presenceDao = new PresenceDao();
        AbsenceDao absenceDao = new AbsenceDao();
        CoursInstanceDao coursInstanceDao =new CoursInstanceDao();
        FicheAppelDao ficheAppelDao = new FicheAppelDao();
        String[] etudiants= request.getParameterValues("etudiantId");
        int ficheAppelId = ficheAppelDao.create(new FicheAppel());
        FicheAppel ficheAppel= ficheAppelDao.find(ficheAppelId);

        HashMap<String, String> erreurs = new HashMap<>();

        if(request.getParameter("coursInstance")!= null){
            System.out.println("test");
            int coursChooseId= Integer.parseInt(request.getParameter("coursInstance"));
            CoursInstance coursChoose= coursInstanceDao.find(coursChooseId);
            coursInstanceDao.updateFicheAppel(coursChoose,ficheAppel);
        }
        for (String etudiant:etudiants) {
            int etudiantId = Integer.parseInt(etudiant);
            Etudiant etudiantAppel = etudiantDao.find(etudiantId);

            if(request.getParameter(etudiant) == null || request.getParameter(etudiant).isEmpty() ) {


            }else{
                String presence= request.getParameter(etudiant);
                if (presence.equals("present")) {
                    presenceDao.setPresenceCours(etudiantAppel, ficheAppel);
                    ficheAppelDao.setValiderTrue(ficheAppel);
                } else if (presence.equals("absent")) {
                    absenceDao.setAbsenceCours(etudiantAppel, ficheAppel);
                    ficheAppelDao.setValiderTrue(ficheAppel);
                } else if (presence.equals("retard")) {
                    presenceDao.setRetardCours(etudiantAppel, ficheAppel);
                    ficheAppelDao.setValiderTrue(ficheAppel);
                }
            }
        }
        response.sendRedirect("/compte/planning");
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
