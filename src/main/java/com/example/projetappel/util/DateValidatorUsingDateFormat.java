package com.example.projetappel.util;

import org.apache.commons.validator.routines.DateValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidatorUsingDateFormat extends DateValidator {

    private final String DATE_FORMAT;

    public DateValidatorUsingDateFormat(String dateFormat) {
        this.DATE_FORMAT = dateFormat;
    }

    @Override
    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.DATE_FORMAT);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
