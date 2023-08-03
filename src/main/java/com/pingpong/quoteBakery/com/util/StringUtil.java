package com.pingpong.quoteBakery.com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {

    public static String convertListToString(String delimiter, List<String> list) {
        return String.join(delimiter, list);
    }

    public static List<String> convertStringToList(String delimiter, String str) {
        if (str == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(str.split(delimiter)));
    }

}
