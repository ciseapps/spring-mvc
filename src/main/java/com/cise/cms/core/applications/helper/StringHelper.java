package com.cise.cms.core.applications.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper {

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static String dateToString(Date date) {
        if (date != null) return new SimpleDateFormat("yyyy-mm-dd").format(date);
        return "";
    }
}
