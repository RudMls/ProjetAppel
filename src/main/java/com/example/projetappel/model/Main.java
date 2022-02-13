package com.example.projetappel.model;

import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.dao.UtilisateurDao;
import com.example.projetappel.util.DatePlanning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class Main {

    public static void main(String[] args) throws ParseException {

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(simpleDateFormat.parse("12-02-2022"));
//        System.out.println("12-02-2022 = " + calendar.getTimeInMillis() + " " + DatePlanning.getWeektoMillis(calendar.getTimeInMillis()));
//        calendar.setTime(simpleDateFormat.parse("02-02-2022"));
//        System.out.println("02-02-2022 = " + calendar.getTimeInMillis() + " " + DatePlanning.getWeektoMillis(calendar.getTimeInMillis()));


        int test = Integer.parseInt("2", 2);

        System.out.println(test);
        //System.out.println(DatePlanning.getWeektoMillis(1643756400000L));

    }
}
