package com.example.projetappel.controller;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.JustificatifDao;
import com.example.projetappel.model.Absence;
import com.example.projetappel.model.Justificatif;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "ScolariteJustificatifController", value = "/compte/scolarite-justificatif")
public class ScolariteJustificatifController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JustificatifDao justificatifDao = new JustificatifDao();
        ArrayList<Justificatif> justificatifs = (ArrayList<Justificatif>) justificatifDao.findAll();
        request.setAttribute("justificatifs", justificatifs);
        request.setAttribute("page", "scolarite-justificatif");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] justificatifsIds = request.getParameterValues("justificatifs_ids");
        ArrayList<Justificatif> justificatifs;
        if (justificatifsIds == null || justificatifsIds.length == 0) {
            doGet(request, response);
        } else {
            JustificatifDao justificatifDao = new JustificatifDao();
            justificatifs = new ArrayList<>();
            Justificatif justificatif = new Justificatif();
            for (String s : Arrays.asList(justificatifsIds)) {
                justificatif = justificatifDao.find(Integer.parseInt(s));
                justificatif.setValidee(true);
                justificatifDao.update(justificatif);
            }
        request.getSession().setAttribute("justificatifs", justificatifs);
        response.sendRedirect("/compte/justificatif");
        }
    }
}
