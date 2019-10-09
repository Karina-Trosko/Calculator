package com.example.simplecalculator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Parser {
    private Parser() {
    }

    private static ArrayList<Pattern> tokensList = new ArrayList<Pattern>(Arrays.asList(Pattern.compile("^[0-9]+(\\.[0-9]+)?"), Pattern.compile("^\\+"), Pattern.compile("^\\*"), Pattern.compile("^\\-"), Pattern.compile("^/"), Pattern.compile("^\\("), Pattern.compile("^\\)")));

    public static ArrayList<String> ParseBySpace(String str) {
        String[] splitStr = str.split(" ");
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(splitStr));
        list.removeAll(Arrays.asList("", null));
        return list;
    }

    public static ArrayList<String> Parse(String str) {
        ArrayList<String> input = new ArrayList<String>();
        boolean found = false;
        String garbage = "";
        while (str.length() > 0) {
            found = false;
            for (Pattern p : tokensList) {
                Matcher m = p.matcher(str);
                if (m.find()) {
                    input.add(m.group());
                    str = str.substring(m.group().length());
                    found = true;
                    break;
                }
            }
            if (!found) {
                str = str.substring(1);
            }

        }
        return input;
    }

}
