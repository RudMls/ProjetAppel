package com.example.projetappel.controller;

import com.example.projetappel.dao.*;
import com.example.projetappel.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CoursInstanceController", value = "/compte/cours-instance")
public class CoursInstanceController extends HttpServlet {
    public  String findAppelEtudiant(Etudiant etudiant, CoursInstance coursInstance){
        String statut="";
        EtudiantDao etudiantDao = new EtudiantDao();
       if(!etudiantDao.getPresenceEtudiantCours(etudiant,coursInstance).isEmpty()){
            Boolean presence =etudiantDao.getPresenceEtudiantCours(etudiant,coursInstance).get(0).isRetard();
            statut = presence ? "retard": "present";
       }
       else if (!etudiantDao.getAbsenceEtudiantCours(etudiant,coursInstance).isEmpty()){
            statut = "absent";
        };
        return statut;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Etudiant,String> listEtudiantPresence= new HashMap<Etudiant,String>();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            Integer coursIntanceId = stringToInteger(id);
            if (coursIntanceId != null) {
                CoursInstanceDao coursInstanceDao = new CoursInstanceDao();
                EtudiantDao etudiantDao = new EtudiantDao();
                ArrayList<Etudiant> listEtudiant =(ArrayList<Etudiant>) etudiantDao.getEtudiantCoursInstance(coursIntanceId);
                CoursInstance coursInstance = coursInstanceDao.find(coursIntanceId);
                for( Etudiant etudiant :listEtudiant){
                    String statut=findAppelEtudiant(etudiant,coursInstance);
                    listEtudiantPresence.put(etudiant,statut);
                }
                request.setAttribute("coursInstance", coursInstance);
                request.setAttribute("listEtudiant", listEtudiant);
                request.setAttribute("listEtudiantPresence",listEtudiantPresence);
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
        //On met à jour l'instance de cours par rapport à la fiche d'appelle crée
        if(request.getParameter("coursInstance")!= null){
            int coursChooseId= Integer.parseInt(request.getParameter("coursInstance"));
            CoursInstance coursChoose= coursInstanceDao.find(coursChooseId);
            coursInstanceDao.updateFicheAppel(coursChoose,ficheAppel);
        }
        //On met à jour le statut des étudiants et si l'utilisateur clic sur valider, on modifie la valeur de la fiche d'appelle
        for (String etudiant:etudiants) {
            int etudiantId = Integer.parseInt(etudiant);
            Etudiant etudiantAppel = etudiantDao.find(etudiantId);

            if(request.getParameter(etudiant) == null || request.getParameter(etudiant).isEmpty() ) {


            }else{
                if(request.getParameter("submit").equals("Enregistrer")){

                }
                else{
                    String presence= request.getParameter(etudiant);
                    if (presence.equals("present")) {
                        presenceDao.setPresenceCours(etudiantAppel, ficheAppel);
                    } else if (presence.equals("absent")) {
                        absenceDao.setAbsenceCours(etudiantAppel, ficheAppel);
                    } else if (presence.equals("retard")) {
                        presenceDao.setRetardCours(etudiantAppel, ficheAppel);
                    }
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
