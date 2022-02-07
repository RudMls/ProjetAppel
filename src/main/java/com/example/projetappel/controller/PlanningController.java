package com.example.projetappel.controller;

import com.example.projetappel.enumtype.ResponseType;
import com.example.projetappel.model.CoursInstance;
import com.example.projetappel.model.Enseignant;
import com.example.projetappel.model.Utilisateur;
import com.example.projetappel.util.DatePlanning;
import com.example.projetappel.util.DateValidatorUsingDateFormat;
import com.example.projetappel.util.ResponseJSON;
import com.google.gson.Gson;
import org.apache.commons.validator.routines.DateValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@WebServlet(name = "PlanningController", value = "/compte/planning")
public class PlanningController extends HttpServlet {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("page", "planning");
        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ResponseJSON responseJSON = new ResponseJSON();
        String erreur = "";
        String date = request.getParameter("date");

        if (date == null || date.trim().isEmpty()) {
            responseJSON.setResponseType(ResponseType.ERREUR);
            responseJSON.setBody("Veillez renseigner une date");
            DateValidator validator = new DateValidatorUsingDateFormat("dd/MM/yyyy");
            if (validator.isValid(date)) {
                responseJSON.setResponseType(ResponseType.SUCCES);
                Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
                if (utilisateur instanceof Enseignant) {
                    try {
                        DatePlanning datePlanning = new DatePlanning(SDF.parse(date));
                        Date firstDayOfWeek = datePlanning.getFirstDayOfWeek();
                        Date lastDayOfWeek = datePlanning.getLastDayOfWeek();

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Enseignant enseignant = (Enseignant) utilisateur;
                    Set<CoursInstance> coursInstances = enseignant.getCoursInstances();
//                    coursInstances.stream()
//                            .filter(coursInstance -> coursInstance.getDateDebut() = );
                }

            } else {
                responseJSON.setResponseType(ResponseType.ERREUR);
                responseJSON.setBody("Veillez renseigner un champ");
            }
        }
        String json = new Gson().toJson(responseJSON);
        response.getWriter().write(json);
    }
}
