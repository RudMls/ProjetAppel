package com.example.projetappel.util;

import com.example.projetappel.dao.CoursInstanceDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.dao.FicheAppelDao;
import com.example.projetappel.enumtype.AppelEtat;
import com.example.projetappel.model.CoursInstance;
import com.example.projetappel.model.Etudiant;
import com.example.projetappel.model.FicheAppel;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class PDFGenerator {

    public static CoursInstanceDao coursInstanceDao = new CoursInstanceDao();
    public static FicheAppelDao ficheAppelDao = new FicheAppelDao();
    public static EtudiantDao etudiantDao = new EtudiantDao();
    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM-yyyy");
    public static final SimpleDateFormat SDF_HH_MM = new SimpleDateFormat("HH:mm");
    public static final Calendar CALENDAR = Calendar.getInstance();

    public static void generer(HttpServletResponse response, int coursInstanceId, String realPath) {


        CoursInstance coursInstance = coursInstanceDao.find(coursInstanceId);
        FicheAppel ficheAppel = coursInstance.getFicheAppel();
        ArrayList<Etudiant> etudiants = new ArrayList<>(etudiantDao.getEtudiantCoursInstance(coursInstanceId));


        ArrayList<Cell> headCells = new ArrayList<>(Arrays.asList(
                new Cell().add("Etudiant"),
                new Cell().add("Présent"),
                new Cell().add("Abscent"),
                new Cell().add("Justificatif")
        ));

        ArrayList<ListItem> listItems = new ArrayList<>(Arrays.asList(
                new ListItem("Enseignant : " + coursInstance.getEnseignant().getPrenom() + " " + coursInstance.getEnseignant().getNom()),
                new ListItem("Cours : " + coursInstance.getCours().getLibelle()),
                new ListItem("Date : " + SDF.format(coursInstance.getDateDebut())),
                new ListItem("Durée : " + SDF_HH_MM.format(coursInstance.getDateFin().getTime() - coursInstance.getDateDebut().getTime()))
        ));

        List list = new List();

        listItems.forEach(list::add);

        Table table = new Table(4);

        // add style
        headCells.forEach(headCell -> {
            headCell.setFontColor(new DeviceRgb(245, 245, 245));
            headCell.setBackgroundColor(Color.DARK_GRAY);
            headCell.setBorder(Border.NO_BORDER);
            headCell.setTextAlignment(TextAlignment.CENTER);
        });

        Document document = null;
        response.setContentType("application/pdf");

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(response.getOutputStream()))) {

            ImageData ut1ImageData = ImageDataFactory.create(realPath + "image/ut1.png");
            ImageData checkImageData = ImageDataFactory.create(realPath + "image/check.png");
            ImageData transparentImageData = ImageDataFactory.create(realPath + "image/transparent.png");
            Image ut1Image = new Image(ut1ImageData)
                    .setWidth(65)
                    .setHeight(65)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            Image checkImage = new Image(checkImageData)
                    .setWidth(10)
                    .setHeight(10)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            Image transparentImage = new Image(transparentImageData)
                    .setWidth(10)
                    .setHeight(10)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            document = new Document(pdfDoc);
            document.add(ut1Image);

            // Titre du document
            document.add(new Paragraph(new Text("Fiche d'appel").setBold()));
            // Informations du document
            document.add(list);
            // En-tête du document
            headCells.forEach(table::addCell);

            for (int i = 0; i < etudiants.size(); i++) {

                table.addCell(
                        new Cell()
                                .add(etudiants.get(i).getPrenom() + " " + etudiants.get(i).getNom())
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(i % 2 == 0 ? Color.WHITE : new DeviceRgb(245, 245, 245))
                                .setTextAlignment(TextAlignment.CENTER)
                );
                table.addCell(
                        new Cell()
                                .add(
                                        Arrays.asList(AppelEtat.PRESENCE, AppelEtat.RETART).contains(AppelManager.getAppelEtat(etudiants.get(i).getId(), ficheAppel.getId())) ? checkImage : transparentImage
                                )
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(i % 2 == 0 ? Color.WHITE : new DeviceRgb(245, 245, 245))
                                .setTextAlignment(TextAlignment.CENTER)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                );
                table.addCell(
                        new Cell()
                                .add(
                                        AppelManager.getAppelEtat(etudiants.get(i).getId(), ficheAppel.getId()) == AppelEtat.ABSENCE ? checkImage : transparentImage
                                )
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(i % 2 == 0 ? Color.WHITE : new DeviceRgb(245, 245, 245))
                                .setTextAlignment(TextAlignment.CENTER)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                );
                table.addCell(
                        new Cell()
                                .add(
                                        AppelManager.getAppelEtat(etudiants.get(i).getId(), ficheAppel.getId()) == AppelEtat.ABSENCE_JUSTIFIE ? checkImage : transparentImage
                                )
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(i % 2 == 0 ? Color.WHITE : new DeviceRgb(245, 245, 245))
                                .setTextAlignment(TextAlignment.CENTER)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                );
            }
            document.add(table);

        } catch (Exception e) {
            e.printStackTrace();
//            request.setAttribute("general_error", e.getMessage());
//            request.getRequestDispatcher("/").forward(request, response);
        } finally {
            assert document != null;
            document.close();
        }

    }
}
