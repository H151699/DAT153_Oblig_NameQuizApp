package com.dat153.andrew.mnamequizeapp.utils;

public class StringManipulation {

    public static String expandUsername(String name){


        return name.replace(".", " ");
    }



    public static String condenseName(String name){

        return name.replace(" ", ".");
    }




}
