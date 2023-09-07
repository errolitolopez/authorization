package com.el.authorization.utils;

import org.apache.commons.lang3.StringUtils;

public class NameUtils {
    public static String replaceSpaceToUnderScore(String... args) {
        String str = "";
        for (String arg : args) {
            if (StringUtils.isNotBlank(arg)) {
                str += "_" + arg.trim().replace("\\s", "_");
            }
        }
        if (StringUtils.isNotBlank(str)) {
            str = str.substring(1).toUpperCase();
        }
        return str.toUpperCase();
    }
}
