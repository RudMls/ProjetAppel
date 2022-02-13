package com.example.projetappel.controller;

import static com.example.projetappel.util.Constants.*;
import static com.example.projetappel.util.ContentType.*;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.FichierDao;
import com.example.projetappel.dao.JustificatifDao;
import com.example.projetappel.model.*;
import com.example.projetappel.util.FileManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "JustificatifController", value = "/compte/justificatif")
public class JustificatifController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("absences") == null) {
            response.sendRedirect("/compte/consultation-etudiant");
        } else {
            request.setAttribute("absences", request.getSession().getAttribute("absences"));
//            request.getSession().removeAttribute("absences");
            request.setAttribute("page", "justificatif");
            request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            upload.setHeaderEncoding(HEADER_ENCODING);
            try {
                List<FileItem> formItems = upload.parseRequest(request);
                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField() && Objects.equals(item.getFieldName(), "fichier")) {
                            String fileName = item.getName();
                            String fileContentType = item.getContentType();
                            if (!Objects.equals(fileContentType, APPLICATION_PDF)) {
                                request.setAttribute("fichier_erreur", "Veuillez déposer un fichier au format \".pdf\"");
                                doGet(request, response);
                                break;
                            } else {
                                Etudiant etudiant = (Etudiant) request.getAttribute("utilisateur");
                                FichierDao fichierDao = new FichierDao();
                                AbsenceDao absenceDao = new AbsenceDao();
                                JustificatifDao justificatifDao = new JustificatifDao();
                                Fichier fichier = new Fichier(fileName, fileContentType);
                                fichier.setId(fichierDao.create(fichier));
                                FileManager.creerFichier(request.getServletContext().getRealPath(""), fichier, item);
                                ArrayList<Absence> absences = (ArrayList<Absence>) request.getSession().getAttribute("absences");
                                Justificatif justificatif = new Justificatif(fichier);
                                justificatifDao.create(justificatif);
                                absences.forEach(absence -> {
                                    absence.setJustificatif(justificatif);
                                    absenceDao.update(absence);
                                    //Un mail est envoyé à la scolarité à chaque insertion de justificatif
                                    String from="ut.capitole.desmob@gmail.com";
                                    String msg="" +
                                            "Bonjour,"+
                                            "\nL'étudiant "+absence.getEtudiant().getPrenom()+" "+absence.getEtudiant().getNom()+" vient d'ajouter un justificatif pour son absence du "+absence.getFicheAppel().getCoursInstance().getParseDateDebut()+" pour le cours "+absence.getFicheAppel().getCoursInstance().getCours().getLibelle()+
                                            "\nVous pouvez la consulter depuis l'application."
                                            +"\nCordialement,"+
                                            "\nDevmob";

                                    String pwd="desmobAppel98";
                                    String sub="Nouvelle absence pour "+absence.getFicheAppel().getCoursInstance().getCours().getLibelle()+ " du "+absence.getFicheAppel().getCoursInstance().getParseDateDebut();
                                    String to="ut.capitole.desmob@gmail.com";
                                    CoursInstanceController.send(from,pwd,to,sub,msg);
                                });
                                request.getSession().removeAttribute("absences");
                                response.sendRedirect("/compte/consultation-etudiant");
                                break;
                            }
                        }
                    }
                } else {
                    request.setAttribute("fichier_erreur", "Veuillez renseigner un document justificatif");
                    doGet(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
