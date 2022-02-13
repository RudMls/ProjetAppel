package com.example.projetappel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidatorUsingDateFormat {

    private final String DATE_FORMAT;

    public DateValidatorUsingDateFormat(String dateFormat) {
        this.DATE_FORMAT = dateFormat;
    }

    public boolean isValid(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(this.DATE_FORMAT);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
