package com.ownutils.test;

import java.util.*;

public class ListDemo {

    public static void main(String[] args) {

        List data = new ArrayList();
        data.add("ddfdf");
        List data1 = new LinkedList();
        Set set = new HashSet();
        Map map = new HashMap();
        map.put("key", "tw");
        map.put("name", "cm");
        System.out.println(map.hashCode());
        Arrays.asList(set);

        String test = "fdfdlfdsf";
        test.hashCode();
        System.out.println(map.getOrDefault("dfdf", "gdf") +  "=========");

        for (Object e : map.entrySet()){

            System.out.println(((Map.Entry)e).getKey());
        }

    }


}
