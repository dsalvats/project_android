package com.xpomanager.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateUtils {

    public static String rawDateToFormated(String rawDate) {
        String string = "";

        rawDate = rawDate.split("T")[0];

        @SuppressLint("SimpleDateFormat")
        DateFormat format2Date = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat")
        DateFormat format2String = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = format2Date.parse(rawDate);
            string = format2String.format(Objects.requireNonNull(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return string;
    }


}
