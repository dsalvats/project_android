package com.xpomanager.utils;

public class StringUtils {

    public static boolean stringContainsString(String inputStr, String[] items) {
        for (String item : items)
            if (stringContainsString(inputStr, item))
                return true;
        return false;
    }

    public static boolean stringContainsString(String inputStr, String item) {
        return inputStr.contains(item);
    }

}
