package com.example.projetappel.controller;

import com.example.projetappel.dao.*;
import com.example.projetappel.model.*;
import com.example.projetappel.util.PDFGenerator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;
import javax.mail.internet.*;



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

    public void enregistrer() {

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

                    for (String etudiant : etudiantIds) {
                        int etudiantId = Integer.parseInt(etudiant);
                        Etudiant etudiantAppel = etudiantDao.find(etudiantId);
                        //Vide la BDD pour les étudiants et la fiche d'appel
                        Absence absence = absenceDao.findByEtudiantFicheAppel(etudiantId, ficheAppel.getId());
                        Presence presence = presenceDao.findByEtudiantFicheAppel(etudiantId, ficheAppel.getId());
                        if (absence != null) absenceDao.delete(absence);
                        if (presence != null) presenceDao.delete(presence);
//
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
                    response.sendRedirect("/compte/cours-instance?id="+coursInstanceId);
                    break;
                case "Valider" :
                    ficheAppel.setValidee(true);
                    ficheAppelDao.update(ficheAppel);
                    //Envoyer le mail à l'étudiant
                    List<Etudiant> allEtudiants= new ArrayList<>();
                    List<Absence> absences= absenceDao.getAbsTotCours(ficheAppel.getId());
                    for (Absence absence:absences){
                        allEtudiants.add(absence.getEtudiant());
                    }
                    for(Absence absence:absences){
                        String from="ut.capitole.desmob@gmail.com";
                        String msg="" +
                                "Bonjour "+absence.getEtudiant().getNom()+" "+absence.getEtudiant().getPrenom()+","+
                                "\nVous avez une nouvelle absence pour le cours "+absence.getFicheAppel().getCoursInstance().getCours().getLibelle()+ " le "+absence.getFicheAppel().getCoursInstance().getParseDateDebut()+ "."+
                                "\nVeuillez ajouter votre justificatif via l'application."
                                +"\nCordialement,"+
                                "\nLa scolarité\n"+
                                "\nUT1 Captitole";

                        String pwd="desmobAppel98";
                        String sub="Nouvelle absence pour "+absence.getFicheAppel().getCoursInstance().getCours().getLibelle()+ " du "+absence.getFicheAppel().getCoursInstance().getParseDateDebut();
                        String to="ut.capitole.desmob@gmail.com";
                        send(from,pwd,to,sub,msg);
                    }
                    response.sendRedirect("/compte/planning");

                    break;
                case "export_pdf" :
                    ArrayList<Etudiant> listEtudiant =(ArrayList<Etudiant>) etudiantDao.getEtudiantCoursInstance(coursInstanceId);
                    PDFGenerator.generer(response, coursInstanceId, request.getServletContext().getRealPath("/"));
                    break;
            }

        }

    }
    public static void send(String from,String pwd,String to,String sub,String msg){
        //Propriétés
        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", "465");
        //Session
        Session s = Session.getDefaultInstance(p,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, pwd);
                    }
                });
        //composer le message
        try {
            MimeMessage m = new MimeMessage(s);
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            m.setSubject(sub);
            m.setText(msg);
            //envoyer le message
            Transport.send(m);
        } catch (MessagingException e) {
            e.printStackTrace();
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
