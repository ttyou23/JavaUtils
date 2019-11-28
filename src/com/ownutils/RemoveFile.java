package com.ownutils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveFile {

    public static void renameFileIndex(String filePath){
        File filelist = new File(filePath);
        if (filelist != null && filelist.listFiles().length > 0){

            int i =1;
            for (File file :filelist.listFiles()){

                System.out.println("file: " + file.getName());
                String newName = filePath + File.separator + i + ".rar";
                System.out.println(newName);
                file.renameTo(new File(newName));
                i++;
            }

        }
    }


    public static void list_file(String root_path){

        File filelist = new File(root_path);
        if (filelist != null && filelist.listFiles().length > 0){

            for (File file :filelist.listFiles()) {

                for(File temp : filelist.listFiles()){

                    if(file.getName().equals(temp.getName()) ){
                        continue;
                    }

                    if(file.length() == temp.length()){
                        System.out.println("====================================");
                        System.out.println(file.getName());
                        System.out.println(temp.getName());
                        System.out.println("====================================");
                        if(file.getName().length() > temp.getName().length()){
                            temp.delete();
                        } else {
                            file.delete();
                        }
                    }
                }

            }
        }
    }

public final static String RENAME_FLAG = " (1)";

    private static void rename(String path){
        File filelist = new File(path);
        if (filelist != null && filelist.listFiles().length > 0) {

            for (File file : filelist.listFiles()) {
                if(file.getName().indexOf(RENAME_FLAG) >= 0){
                    String newPath = file.getAbsolutePath().replaceAll(RENAME_FLAG, "");
                    System.out.println("old: " + file.getAbsolutePath());
                    System.out.println("new: " + newPath);
                    file.renameTo(new File(newPath));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private final static String PRE_PATH = "D:\\book\\lcy\\data\\";
    private final static String END_PATH = "D:\\book\\lcy\\转好数据\\";
    private final static String PRE_FLAG = "SURF_CLI_CHN_MUL_";
    private final static String END_FLAG = "";

    private static void LCY(String path){
        File filelist = new File(path);
        if ( filelist.listFiles().length > 0) {
            for (File file : filelist.listFiles()) {
                if (file.isDirectory()){
                    for (File subfile: file.listFiles()){
                        renameLCY(subfile, "小金");
                        renameLCY(subfile, "康定");
                        renameLCY(subfile, "西昌");
                        renameLCY(subfile, "会理");
                    }
                }
            }
        }
    }

    private static void renameLCY(File file, String key){

        if(file.isFile() && file.getName().indexOf(key) >= 0){
            String newPath = file.getParent() + File.separator + key + File.separator + file.getName().replaceAll(PRE_FLAG, END_FLAG);
            newPath = newPath.replace(PRE_PATH, END_PATH);
            System.out.println(newPath);
            File newFile = new File(newPath);
            if (!newFile.getParentFile().exists()){
                newFile.getParentFile().mkdirs();
            }
            file.renameTo(newFile);
        }
    }


    private static void LCY_rename(String path){
        File filelist = new File(path);
        if ( filelist.listFiles().length > 0) {
            for (File file : filelist.listFiles()) {
                if (file.isDirectory()){
                    for (File subfile: file.listFiles()){
                        if (file.isDirectory()){
                            for (File tempfile: subfile.listFiles()){
                                if(tempfile.getName().indexOf(PRE_FLAG) >= 0){
                                    String newPath = tempfile.getAbsolutePath().replaceAll(PRE_FLAG, "");
                                    System.out.println(newPath);
                                    tempfile.renameTo(new File(newPath));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void LCY_findmiss(String path){
        File filelist = new File(path);
        if ( filelist.listFiles().length > 0) {
            Map<String, Map> map = new HashMap<>();
            for (File file : filelist.listFiles()) {
                if (file.isDirectory()){
                    String flag = file.getName();
                    System.out.println(file.getName());
                    List<String> tempdate = getDate();
                    Map<String, String> datemap = new HashMap<>();
                    for (String s : tempdate){
//                        System.out.println(key + " " + s);
                        datemap.put(s, "--");
                    }

                    for (File subfile: file.listFiles()){
                        String name = subfile.getName();
                        int size = name.length();
                        String date = name.substring(size-10, size-4);
                        datemap.put(date, "oo");
                    }
                    map.put(flag,datemap );
                }
            }
//            for (Map.Entry<String, Map> entry : map.entrySet()) {
//                System.out.println(entry.getKey() + " " + entry.getValue());
//            }

//            System.out.print(String.format("%6s", "时间"));
//            System.out.print(String.format("%6s", "地温"));
//            System.out.print(String.format("%6s", "日照"));
//            System.out.print(String.format("%6s", "本站气压"));
//            System.out.print(String.format("%6s", "气温"));
//            System.out.print(String.format("%6s", "相对湿度"));
//            System.out.print(String.format("%6s", "蒸发"));
//            System.out.print(String.format("%6s", "降水"));
//            System.out.print(String.format("%6s", "风向风速"));
//            System.out.println(" ");

            List<String> tempdate = getDate();
            for (String s : tempdate){
                System.out.print(String.format("%6s", s));
//                System.out.print(String.format("%6s", map.get("地温").getOrDefault(s, "--")));
                System.out.print(String.format("%6s", map.get("日照").getOrDefault(s, "--")));
//                System.out.print(String.format("%6s", map.get("本站气压").getOrDefault(s, "--")));
                System.out.print(String.format("%6s", map.get("气温").getOrDefault(s, "--")));
                System.out.print(String.format("%6s", map.get("相对湿度").getOrDefault(s, "--")));
//                System.out.print(String.format("%6s", map.get("蒸发").getOrDefault(s, "--")));
//                System.out.print(String.format("%6s", map.get("降水").getOrDefault(s, "--")));
                System.out.print(String.format("%6s", map.get("风向风速").getOrDefault(s, "--")));
                System.out.println(" ");
            }
        }
    }

    private static List<String> getDate(){
        List<String> date = new ArrayList<>();
        for (int i = 1960; i < 2017; i++) {

            for (int j = 1; j < 13; j++) {
                String temp = String.format("%d%02d", i, j);
//                System.out.println(temp);
                date.add(temp);
            }
        }
        return date;
    }



    public static void main(String[] args) {

//        LCY("D:\\book\\lcy\\data");
//        LCY_rename("D:\\book\\lcy\\转好数据");
        LCY_findmiss("D:\\book\\lcy\\data");
//        rename("D:\\book\\lcy\\data\\风向风速");
    }
}
