package com.ownutils;

import com.ownutils.test.Animal;

import java.util.ArrayList;
import java.util.List;

public class Jvm {

    final static int index = 1;
    static String data = "test";

    public Jvm(){}

    public static void setData(String data) {
        Jvm.data = data;
    }

    private void func1(){

    }

    protected void func2() {
    }

    public void func3() {
    }


    public int getIndex(){
        int in ;
        in = 10;
        return in;
    }



    public static void main(String[] args) {

        Jvm jvm = new Jvm();
        int res = jvm.getIndex();

        List<String> test = new ArrayList<>();
        test.add("qqq");
        test.add("www");
        test.add("ddd");

        test.forEach(item -> System.out.println(item));
        System.out.println();


    }


}
