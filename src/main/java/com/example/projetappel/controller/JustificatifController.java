package com.example.projetappel.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "JustificatifController", value = "/justificatif")
public class JustificatifController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("page", "justificatif");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, String> erreurs = new HashMap<>();
        String libelle = request.getParameter("libelle");

        String contentType = request.getContentType();

        if (contentType.contains("multipart/form-data")) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File("c:\\temp"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(MAX_FILE_SIZE);

            try {
                List fileItems = upload.parseRequest(request);
                Iterator iterator = fileItems.iterator();
                while (iterator.hasNext()) {
                    FileItem fileItem = (FileItem) iterator.next();
                    if (!fileItem.isFormField()) {
                        String fieldName = fileItem.getFieldName();
                        String fileName = fileItem.getName();
                        boolean isInMemery = fileItem.isInMemory();
                        long sizzInBytes = fileItem.getSize();
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }

        if (libelle == null || libelle.isEmpty()) {
            erreurs.put("libelle_erreur", "Veillez renseigner le libellé");
        }

    }
}
