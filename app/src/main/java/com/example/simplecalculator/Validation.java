package com.example.simplecalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validation {
    private Validation() {
    }

    private static ArrayList<Pattern> tokensList = new ArrayList<Pattern>(Arrays.asList(Pattern.compile("^[\\-\\+\\*/][\\-\\+\\*/]+"),Pattern.compile("[\\-\\+\\*/]$"),Pattern.compile("[\\-\\+\\*/]\\)")));


    public static Boolean IsValid(String str) {
        if (IsValidContent(str) && IsEnoughBrackets(str) && IsCorrectExpression(str)) {
            return true;
        } else {
            return false;
        }
    }

    private static Boolean IsValidContent(String str) {
        if (str.matches("[()*+-/ [0-9]]+"))
            return true;
        else return false;
    }

    private static Boolean IsEnoughBrackets(String str) {
        int countOpenBrackets = str.length() - str.replace("(", "").length();
        int countCloseBrackets = str.length() - str.replace(")", "").length();
        if (countCloseBrackets == countOpenBrackets)
            return true;
        else return false;
    }

    private static Boolean IsCorrectExpression(String str) {
        boolean found = false;
        String garbage = "";
        while (str.length() > 0) {
            found = false;
            for (Pattern p : tokensList) {
                Matcher m = p.matcher(str);
                if (m.find()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                str = str.substring(1);
            } else break;

        }
        if (found) return false;
        else return true;
    }

}
