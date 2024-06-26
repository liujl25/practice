package com.liujunlong.practice.basis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
class RegExpTest {

    private static Pattern p = Pattern.compile(".*?(?=\\()");

    public static void main(String[] args) {

        String str = "成都市(成华区)(武侯区)(高新区)";
        Matcher m = p.matcher(str);
        if(m.find()) {
            System.out.println(m.group());
        }
        boolean matches = str.matches(".*?(?=\\()");
    }
}