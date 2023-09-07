package com.el.authorization.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtils {
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+(?:[_.-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:[_.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,3}$";
    public static final String USERNAME_PATTERN = "(?!.*[\\.\\-\\_]{2,})^[a-zA-Z0-9\\.\\-\\_]{5,20}$";
    public static final String STRONG_PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{8,32})";

    public static boolean isMatch(String regEx, String input) {
        if (StringUtils.isBlank(input)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean isMatch(String[] patterns, String input) {
        for (String regEx : patterns) {
            if (isMatch(regEx, input)) {
                return true;
            }
        }
        return false;
    }
}
