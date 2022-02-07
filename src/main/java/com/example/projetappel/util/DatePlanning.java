package com.example.projetappel.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePlanning {

    private static final SimpleDateFormat SDP = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Date firstDayOfWeek;
    private Date lastDayOfWeek;

    public DatePlanning(Date date) {
        loadDayOfWeek(date);
    }

    private void loadDayOfWeek(Date date) {
        // Get calendar set to current date and time
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // Print dates of the current week starting on Monday
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        this.firstDayOfWeek = c.getTime();
        for (int i = 0; i <6; i++) {
            c.add(Calendar.DATE, 1);
        }
        this.lastDayOfWeek = c.getTime();
    }

    public static boolean isWithinRange(Date date, Date startDate, Date endDate) {
        return !(date.before(startDate) || date.after(endDate));
    }

    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static void main(String[] args) throws ParseException {

        // Get calendar set to current date and time
        Calendar c = Calendar.getInstance();
        c.setTime(SDP.parse("09-02-2022 14:00:00"));

        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println();
        // Print dates of the current week starting on Monday
        DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");
        System.out.println(df.format(c.getTime()));
        for (int i = 0; i <6; i++) {
            c.add(Calendar.DATE, 1);
        }
        System.out.println(df.format(c.getTime()));
        System.out.println();
    }

    public Date getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public void setFirstDayOfWeek(Date firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public Date getLastDayOfWeek() {
        return lastDayOfWeek;
    }

    public void setLastDayOfWeek(Date lastDayOfWeek) {
        this.lastDayOfWeek = lastDayOfWeek;
    }
}
