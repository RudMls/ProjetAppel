package com.example.projetappel.util;

import com.example.projetappel.model.Fichier;
import org.apache.commons.fileupload.FileItem;

import java.io.File;

import static com.example.projetappel.util.Constants.UPLOAD_DIRECTORY;

public class FileManager {

    public static void creerFichier(String realPath, Fichier fichier, FileItem item) {
        try {
            String uploadPath = realPath + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            uploadPath += File.separator + fichier.getId();
            uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String filePath = uploadPath + File.separator + fichier.getNom();
            File storeFile = new File(filePath);
            item.write(storeFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFichier(Fichier fichier) {
        return File.separator + UPLOAD_DIRECTORY +  File.separator + fichier.getId() + File.separator + fichier.getNom();
    }

    public static void deleteFichier(int fichierId) {

    }
}
