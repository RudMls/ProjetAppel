package com.example.projetappel.model;

import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.dao.UtilisateurDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class Main {

    public static void main(String[] args) {

        // Create Formatter class object
        Formatter format = new Formatter();

        // Creating a calendar
        Calendar gfg_calender = Calendar.getInstance();

        // Displaying hour using Format clas using  format
        // specifiers
        // '%tl' for hours and '%tM' for minutes
        format = new Formatter();
        format.format("%tl:%tM", gfg_calender,
                gfg_calender);

        // Printing the current hour and minute
        System.out.println(format);

    }
}
