package com.programming.productservice.service;

import java.util.regex.Pattern;

public class Testing {

    private static final Pattern pattern = Pattern.compile("[A-Za-z0-9]+(,[A-Za-z0-9]+)*$");
    private static final Pattern pattern1 = Pattern.compile("^((?=[A-Za-z0-9, ])(?![_\\\\\\\\-]).)*$");

    public static void main(String[] args) {

        String s="Himanshu12, KAML78,   SHASHI784";
        Boolean b=pattern1.matcher("Himanshu12, kAKLASM45").matches();
        System.out.println(b);
//        Boolean b=Pattern.compile(".s").matcher("as").matches();
        if (s.length() > 100 || !pattern1.matcher(s).matches()) {
            System.out.println(b);
        }
        System.out.println(Pattern.compile("^((?=[A-Za-z0-9, ])(?![_\\\\\\\\-]).)*$").matcher("Hianshu12,  kakmal12").matches());
    }
}
