package com.example.projetappel.controller;

import com.example.projetappel.dao.*;
import com.example.projetappel.model.*;
import com.example.projetappel.util.PDFGenerator;

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
            Boolean retard =etudiantDao.getPresenceEtudiantCours(etudiant,coursInstance).get(0).isRetard();
            statut = retard ? "retard": "present";
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
            Integer coursInstanceId = stringToInteger(id);
            if (coursInstanceId != null) {
                CoursInstanceDao coursInstanceDao = new CoursInstanceDao();
                EtudiantDao etudiantDao = new EtudiantDao();
                ArrayList<Etudiant> listEtudiant =(ArrayList<Etudiant>) etudiantDao.getEtudiantCoursInstance(coursInstanceId);
                CoursInstance coursInstance = coursInstanceDao.find(coursInstanceId);
                for( Etudiant etudiant :listEtudiant){
                    String statut=findAppelEtudiant(etudiant,coursInstance);
                    listEtudiantPresence.put(etudiant,statut);
                }
                request.getSession().setAttribute("coursInstanceId", coursInstanceId);
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

        Integer coursInstanceId = (Integer) request.getSession().getAttribute("coursInstanceId");
        String submit = request.getParameter("submit");
        EtudiantDao etudiantDao = new EtudiantDao();
        PresenceDao presenceDao = new PresenceDao();
        AbsenceDao absenceDao = new AbsenceDao();
        CoursInstanceDao coursInstanceDao = new CoursInstanceDao();
        FicheAppelDao ficheAppelDao = new FicheAppelDao();
        HashMap<String, String> erreurs = new HashMap<>();
        String[] etudiantIds = request.getParameterValues("etudiantId");

        CoursInstance coursInstance = coursInstanceDao.find(coursInstanceId);
        FicheAppel ficheAppel = coursInstance.getFicheAppel();
        //On retrouve le cours choisi dans le planning

        if (submit == null || submit.isEmpty()) {
            doGet(request, response);
        } else {
            switch (submit) {
                case "Enregistrer" :
                    //on crée la fiche d'appel si on clic sur "enregistrer"
//                    int ficheAppelId = ficheAppelDao.create(new FicheAppel());
//                    FicheAppel ficheAppel= ficheAppelDao.find(ficheAppelId);
//                    int coursChooseId = Integer.parseInt(request.getParameter("coursInstance"));
//                    CoursInstance coursChoose = coursInstanceDao.find(coursChooseId);
                    //On met à jour l'instance de cours par rapport à la fiche d'appelle crée
//                    coursInstanceDao.updateFicheAppel(coursChoose,ficheAppel);
                    //On met à jour le statut des étudiants et si l'utilisateur clic sur valider, on modifie la valeur de la fiche d'appelle
                    for (String etudiant : etudiantIds) {
                        int etudiantId = Integer.parseInt(etudiant);
                        Etudiant etudiantAppel = etudiantDao.find(etudiantId);
                        //Vide la BDD pour les étudiants et la fiche d'appel
                        Absence absence = absenceDao.findByEtudiantFicheAppel(etudiantId, ficheAppel.getId());
                        Presence presence = presenceDao.findByEtudiantFicheAppel(etudiantId, ficheAppel.getId());
                        if (absence != null) absenceDao.delete(absence);
                        if (presence != null) presenceDao.delete(presence);
//                        presenceDao.deleteByEtudiantFicheAppel(etudiantId,ficheAppel.getId());
//                        absenceDao.deleteByEtudiantFicheAppel(etudiantId,ficheAppel.getId());
                        //Le nom de la radio récupère la présence
                        String appel = request.getParameter(etudiant) == null ? "" : request.getParameter(etudiant);
                        if (appel.equals("present")) {
                            presenceDao.createOrUpdate(new Presence(etudiantAppel,ficheAppel,false));
                        } else if (appel.equals("absent")) {
                            absenceDao.createOrUpdate(new Absence(etudiantAppel,ficheAppel));
                        } else if (appel.equals("retard")) {
                            presenceDao.createOrUpdate(new Presence(etudiantAppel,ficheAppel,true));
                        }
                    }
                    break;
                case "Valider" :
                    ficheAppel.setValidee(false);
                    ficheAppelDao.update(ficheAppel);
//                    FicheAppel ficheAppel;
//                    if(request.getParameter("coursInstance")!= null){
//                        //On retrouve le cours choisi dans le planning
//                        CoursInstance coursChoose= coursInstanceDao.find(coursInstanceId);
//                        if(coursChoose.getFicheAppel()!=null){
//                            int ficheAppelChoose = coursChoose.getFicheAppel().getId();
//                            ficheAppel=ficheAppelDao.find(ficheAppelChoose);
//                            ficheAppelDao.setValiderTrue(ficheAppel);
//                        }
//                    }
                    break;
                case "export_pdf" :
                    ArrayList<Etudiant> listEtudiant =(ArrayList<Etudiant>) etudiantDao.getEtudiantCoursInstance(coursInstanceId);
                    PDFGenerator.generer(response, coursInstanceId, request.getServletContext().getRealPath("/"));
                    break;
            }
            response.sendRedirect("/compte/planning");
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
