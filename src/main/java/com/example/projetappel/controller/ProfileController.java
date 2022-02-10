package com.example.projetappel.controller;

import com.example.projetappel.dao.*;
import com.example.projetappel.enumtype.Role;
import com.example.projetappel.model.*;
import com.example.projetappel.util.FileManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.example.projetappel.util.Constants.*;
import static com.example.projetappel.util.Constants.HEADER_ENCODING;
import static com.example.projetappel.util.ContentType.ANY_IMAGE_TYPE;
import static com.example.projetappel.util.ContentType.APPLICATION_PDF;

@WebServlet(name = "ProfileController", value = "/compte/profile")
public class ProfileController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ((Role) request.getAttribute("role") == Role.ETUDIANT ) {
            Etudiant etudiant = (Etudiant) request.getAttribute("utilisateur");
            Cours cours = new Cours();

            EtudiantDao etudiantDao = new EtudiantDao();

            //get nombre généralles d'absences par étudiant
            AbsenceDao absenceDao = new AbsenceDao();
            ArrayList<Absence> absences = (ArrayList<Absence>) absenceDao.getAbsences(etudiant.getId());
            float nbAbs = ((ArrayList<Absence>) absenceDao.getAbsences(etudiant.getId())).size();

            //get nombre absences d'un étudiant pour un cours donné
            ArrayList<Absence> absCours = (ArrayList<Absence>) absenceDao.getAbsCours(etudiant.getId(), cours.getId());
            float nbAbsCours = ((ArrayList<Absence>) absenceDao.getAbsCours(etudiant.getId(), cours.getId())).size();

            //get nombre général d'instances de cours où un étudiant était censé participer
            CoursInstanceDao coursInstanceDao = new CoursInstanceDao();
            ArrayList<CoursInstance> coursInstances = (ArrayList<CoursInstance>) coursInstanceDao.getCoursInstances(etudiant.getId());
            float nbInstances = ((ArrayList<CoursInstance>) coursInstanceDao.getCoursInstances((etudiant.getId()))).size();

            //get nombre d'instances de cours où un étudiant donné était censé participer pour un cours donné
            ArrayList<CoursInstance> ciCours = (ArrayList<CoursInstance>) coursInstanceDao.getCiCours(etudiant.getId(), cours.getId());
            float nbInstCours = ((ArrayList<CoursInstance>) coursInstanceDao.getCiCours(etudiant.getId(), cours.getId())).size();

            //get nombre général de retards d'un étudiant donné
            PresenceDao presenceDao = new PresenceDao();
            float nbRetards = ((ArrayList<Presence>) presenceDao.getPresences(etudiant.getId())).size();

            //get nombre de retards d'un étudiant doné pour un cours donné
            float nbRetCours = ((ArrayList<Presence>) presenceDao.getPresCours(etudiant.getId(), cours.getId())).size();

            //get taux général d'absences d'un étudiant
            float txAbsGen = (nbAbs / nbInstances) * 100;
            //get taux d'absences d'un étudiant par cours
            float txAbsCours = (nbAbsCours / nbInstCours) * 100;
            //get taux général de retards d'un étudiant
            float txRetGen = (nbRetards / nbInstances) * 100;
            //get taux de retards d'un étudiant donné pour un cours donné
            float txRetCours = (nbRetCours / nbInstCours) * 100;

            request.setAttribute("txAbsGen", txAbsGen);
            request.setAttribute("txAbsCours", txAbsCours);
            request.setAttribute("txRetGen", txRetGen);
            request.setAttribute("txRetCours", txRetCours);


        }

        request.setAttribute("page", "profile");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
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
                            if (!ANY_IMAGE_TYPE.contains(fileContentType)) {
                                request.setAttribute("fichier_erreur", "Veuillez déposer un fichier au format \".png ou .jpeg ou .jfif ou .gif\"");
                                doGet(request, response);
                                break;
                            } else {
                                Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
                                FichierDao fichierDao = new FichierDao();
                                UtilisateurDao utilisateurDao = new UtilisateurDao();
                                Fichier fichier = new Fichier(fileName, fileContentType);
                                fichier.setId(fichierDao.create(fichier));
                                utilisateur.setFichier(fichier);
                                utilisateurDao.update(utilisateur);
                                FileManager.creerFichier(request.getServletContext().getRealPath(""), fichier, item);
                                response.sendRedirect("/compte/profile");
                                break;
                            }
                        }
                    }
                } else {
                    request.setAttribute("fichier_erreur", "Veuillez renseigner une image");
                    doGet(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
