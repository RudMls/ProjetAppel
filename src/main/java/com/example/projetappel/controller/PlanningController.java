package com.example.projetappel.controller;

import com.example.projetappel.enumtype.ResponseType;
import com.example.projetappel.model.*;
import com.example.projetappel.util.DatePlanning;
import com.example.projetappel.util.DateValidatorUsingDateFormat;
import com.example.projetappel.util.ResponseJSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.validator.routines.DateValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "PlanningController", value = "/compte/planning")
public class PlanningController extends HttpServlet {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat SDF_OLD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SDF_NEW_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
        Enseignant enseignant = (Enseignant) utilisateur;
        Date date = new Date();
        int dayOfWeek;
        long dayMonth;
        String strDate = request.getParameter("date");
        Calendar calendar = Calendar.getInstance();
        String planningAction = request.getParameter("planning_action");
        HashMap<Long, ArrayList<CoursInstance>> coursInstanceFilter = new HashMap<>();

        try {
            if (!(planningAction == null || planningAction.isEmpty()) && !(strDate == null || strDate.isEmpty())) {
                date = SDF_OLD_FORMAT.parse(strDate);
                calendar.setTime(date);
                switch (planningAction) {
                    case "previous" :
                        calendar.add(Calendar.WEEK_OF_MONTH, -1);
                        date = calendar.getTime();
                        break;
                    case "next" :
                        calendar.add(Calendar.WEEK_OF_MONTH, 1);
                        date = calendar.getTime();
                        break;
                }
            }
            calendar.setTime(SDF.parse(SDF.format(date)));
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            Date firstDayOfWeek = calendar.getTime();
            for (int i = 2; i <= 7; i++) {
                coursInstanceFilter.put(calendar.getTimeInMillis(), new ArrayList<>());
                calendar.add(Calendar.DATE, 1);
            }
            Date lastDayOfWeek = calendar.getTime();

            Set<CoursInstance> coursInstances = enseignant.getCoursInstances();
            for (CoursInstance coursInstance : coursInstances) {
                if (DatePlanning.isWithinRange(coursInstance.getDateDebut(), firstDayOfWeek, lastDayOfWeek)) {
                    calendar.setTime(coursInstance.getDateDebut());
//                    dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                    dayMonth = SDF.parse(SDF.format(coursInstance.getDateDebut())).getTime();
                    if (coursInstanceFilter.containsKey(dayMonth)) {
                        coursInstanceFilter.get(dayMonth).add(coursInstance);
                    } else {
                        coursInstanceFilter.put(dayMonth, new ArrayList<>(Collections.singletonList(coursInstance)));
                    }
                }
            }
            request.setAttribute("date", DatePlanning.getStrFormat(date, "yyyy-MM-dd"));
            request.setAttribute("coursInstanceFilter", new TreeMap<>(coursInstanceFilter));
            request.setAttribute("page", "planning");
            request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
//    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
//        Enseignant enseignant = (Enseignant) utilisateur;
//        Date date = new Date();
//        String erreur = null;
//        int dayOfWeek;
//        String strDate = request.getParameter("date");
//        Calendar calendar = Calendar.getInstance();
//        String planningAction = request.getParameter("planning_action");
//        DatePlanning datePlanning = null;
//        HashMap<Integer, ArrayList<CoursInstance>> coursInstanceFilter = new HashMap<>();
//        for (int i = 2; i <= 7; i++) {
//            coursInstanceFilter.put(i, new ArrayList<>());
//        }
//        try {
//            if (!(planningAction == null || planningAction.isEmpty()) && !(strDate == null || strDate.isEmpty())) {
//                date = SDF_OLD_FORMAT.parse(strDate);
//                calendar.setTime(date);
//                switch (planningAction) {
//                    case "search" :
//                        datePlanning = new DatePlanning(date);
//                        break;
//                    case "previous" :
//                        calendar.add(Calendar.WEEK_OF_MONTH, -1);
//                        date = calendar.getTime();
//                        break;
//                    case "next" :
//                        calendar.add(Calendar.WEEK_OF_MONTH, 1);
//                        date = calendar.getTime();
//                        break;
//                }
//            }
//            datePlanning = new DatePlanning(date);
//            Date firstDayOfWeek = datePlanning.getFirstDayOfWeek();
//            Date lastDayOfWeek = datePlanning.getLastDayOfWeek();
//            Set<CoursInstance> coursInstances = enseignant.getCoursInstances();
//            for (CoursInstance coursInstance : coursInstances) {
//                if (datePlanning.isWithinRange(coursInstance.getDateDebut(), firstDayOfWeek, lastDayOfWeek)) {
//                    calendar.setTime(coursInstance.getDateDebut());
//                    dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//                    if (coursInstanceFilter.containsKey(dayOfWeek)) {
//                        coursInstanceFilter.get(dayOfWeek).add(coursInstance);
//                    } else {
//                        coursInstanceFilter.put(dayOfWeek, new ArrayList<>(Collections.singletonList(coursInstance)));
//                    }
//                }
//            }
//            request.setAttribute("date", DatePlanning.getStrFormat(date, "yyyy-MM-dd"));
//            request.setAttribute("coursInstanceFilter", coursInstanceFilter);
//            request.setAttribute("page", "planning");
//            request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
//        Date date = new Date();
//        int hour = date.getHours();
//        int dayOfWeek;
//        HashMap<Integer, ArrayList<CoursInstance>> coursInstanceFilter = new HashMap<>();
//        for (int i = 2; i <= 7; i++) {
//            coursInstanceFilter.put(i, new ArrayList<>());
//        }
//        Calendar calendar = Calendar.getInstance();
//        Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
//        Enseignant enseignant = (Enseignant) utilisateur;
//        DatePlanning datePlanning = new DatePlanning(date);
//        Date firstDayOfWeek = datePlanning.getFirstDayOfWeek();
//        Date lastDayOfWeek = datePlanning.getLastDayOfWeek();
//        Set<CoursInstance> coursInstances = enseignant.getCoursInstances();
//        for (CoursInstance coursInstance : coursInstances) {
//            if (datePlanning.isWithinRange(coursInstance.getDateDebut(), firstDayOfWeek, lastDayOfWeek)) {
//                calendar.setTime(coursInstance.getDateDebut());
//                dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//                if (coursInstanceFilter.containsKey(dayOfWeek)) {
//                    coursInstanceFilter.get(dayOfWeek).add(coursInstance);
//                } else {
//                    coursInstanceFilter.put(dayOfWeek, new ArrayList<>(Collections.singletonList(coursInstance)));
//                }
//            }
//        }
//        request.setAttribute("coursInstanceFilter", coursInstanceFilter);
//        request.setAttribute("page", "planning");
//        request.getRequestDispatcher("/view/compte/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
//        DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat("dd-MM-yyyy");
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        ArrayList<CoursInstance> coursInstancesResponse = new ArrayList<>();
//        HashMap<String, Object> hashMapResponse = new HashMap<>();
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = gsonBuilder.create();
//        String json = null;
//        ResponseJSON responseJSON = new ResponseJSON();
//        String dateParam = request.getParameter("date");
//        if (dateParam == null || dateParam.trim().isEmpty()) {
//            responseJSON.setResponseType(ResponseType.ERREUR);
//            responseJSON.setBody("Veillez renseigner un champ");
//        } else if (!validator.isValid(dateParam)) {
//            responseJSON.setResponseType(ResponseType.ERREUR);
//            responseJSON.setBody("Veillez renseigner une date valide");
//        } else {
//            try {
//                Date date = SDF_OLD_FORMAT.parse(dateParam);
//                responseJSON.setResponseType(ResponseType.SUCCES);
//                Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
//                Enseignant enseignant = (Enseignant) utilisateur;
//                DatePlanning datePlanning = new DatePlanning(date);
//                Date firstDayOfWeek = datePlanning.getFirstDayOfWeek();
//                Date lastDayOfWeek = datePlanning.getLastDayOfWeek();
//                Set<CoursInstance> coursInstances = enseignant.getCoursInstances();
//                for (CoursInstance coursInstance : coursInstances) {
//                    if (datePlanning.isWithinRange(coursInstance.getDateDebut(), firstDayOfWeek, lastDayOfWeek)) {
//                        coursInstancesResponse.add(
//                                new CoursInstance(
//                                        coursInstance.getId(),
//                                        coursInstance.getDateDebut(),
//                                        coursInstance.getDateFin(),
//                                        new Cours(
//                                                coursInstance.getCours().getLibelle()
//                                        )
//                                )
//                        );
//                    }
//                }
//                ArrayList<CoursInstance> coursInstanceFiltre = coursInstances.stream()
//                        .filter(coursInstance -> datePlanning.isWithinRange(date, firstDayOfWeek, lastDayOfWeek))
//                        .collect(Collectors.toCollection(ArrayList::new));
//                responseJSON.setBody(coursInstancesResponse);
//                json = gson.toJson(responseJSON);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        response.getWriter().write(json);
    }
}
