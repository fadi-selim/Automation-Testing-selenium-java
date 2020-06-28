package com.company;

import com.mifmif.common.regex.Generex;


public class GeneratorRegex {

    String valid_name()
    {
        //valid name starts with capital letter and contain no digits
        Generex generex = new Generex("[A-Z]+([a-c]+)");

//        // generate the second String in lexicographical order that match the given Regex.
//        String secondString = generex.getMatchedString(2); [0-3]([a-c]|[e-g]{1,2})
        return generex.random();

    }

    String invalid_name()
    {
        //invalid name start with small letter or digits
        Generex generex = new Generex("([a-z]+|[0-9]){2}([A-Za-z0-9]*)");

        return generex.random();

    }

    String valid_phone()
    {
        //valid phone with 01 and right length
        Generex generex = new Generex("(01)([0-9]){9}");

        return generex.random();

    }

    String invalid_phone()
    {
        //invalid phone starts with "01" and right length but with char
        //OR right length no start 0
        Generex generex = new Generex("([1-9]{1}[0-9]{10})|((01)[A-Za-z0-9]{9})");

        return generex.random();

    }

    String valid_email()
    {
        //valid email with [@] and domain contain no digits and [.com]
        Generex generex = new Generex("([a-z]{5})[@][a-z]{5}");

        String regex = generex.random();
        return generex.random()+".com";

    }


    String invalid_email()
    {


        //not valid mail example@domaincom
        //not valid mail exmpledomain.com
        //choose between the above randomly

        int x = (int)(Math.random() * ((2 - 1) + 1)) + 1;
        if (x==1) {Generex generex = new Generex("([A-Za-z0-9]{5})[@][a-z]{5}");
            String regex = generex.random();
            return regex+"com";
        }

        else {
            Generex generex = new Generex("([A-Za-z0-9]{5})");
            String regex = generex.random();
            return regex+".com";
        }


    }


    String valid_pass()
    {

        //generate pass with minimum one capital one small and 6 char min length
        Generex generex = new Generex("([A-Z]{1}[a-z]{1}[A-Za-z0-9]{4})[A-Za-z0-9]*");
        String regex = generex.random();
        //if length exceeds 8 substring else same
        return (regex.length()>8)?regex.substring(0, 8):regex ;


    }

    String invalid_pass()
    {
        //(generate right length ,wrong structure) OR (right structure ,wrong length)
        Generex generex = new Generex("([A-Z0-9]){8}|([a-z0-9]){8}|([a-z0-9]){6}|([a-z0-9]){6}|([A-Z]{1}[a-z]{1}[A-Za-z0-9]{8})");

        return generex.random();

    }

}
