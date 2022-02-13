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


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
