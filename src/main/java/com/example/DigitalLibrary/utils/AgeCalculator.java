package com.example.DigitalLibrary.utils;

import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator {

    public static String calculateAgeFormatted(LocalDate dob) {

        Period period = Period.between(dob, LocalDate.now());

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        String yearStr = String.format("%02d", years);
        String monthStr = String.format("%02d", months);

        String dayStr;
        if (days < 100) {
            dayStr = String.format("%02d", days);
        } else {
            dayStr = String.valueOf(days);
        }

        return yearStr + " years " + monthStr + " months " + dayStr + " days";
    }
}
