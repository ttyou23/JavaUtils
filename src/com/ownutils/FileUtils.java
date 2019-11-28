package com.ownutils;

import java.io.File;

public class FileUtils {

    private final static String DIR_DEST = "F:\\ChromeDownload\\佛爷\\（免费）巴比伦网站全部资源\\1pan";
    private static int index = 1;

    private static void showFiles(String path){

        File file = new File(path);
        for (int i = 0; i < file.listFiles().length; i++) {

            File subfile = file.listFiles()[i];

            if (subfile.isDirectory()){
                showFiles(subfile.getAbsolutePath());
//                renameFile(subfile.getAbsolutePath());
            } else {
                renameFile(subfile.getAbsolutePath());
            }
        }
    }

    private static void renameFile(String path){
//        File file = new File(path);
//        for (File sub : file.listFiles()) {
//            System.out.println(sub.getAbsolutePath());
//
//        }
        System.out.println(index + "  " + path);
        File file = new File(path);
        String newFile = DIR_DEST + File.separator + index + "_" + file.getName();
//        boolean res = file.renameTo(new File(newFile));
//        if (!res){
//            System.out.println(index + "  " + path);
//        }
        index++;

    }

    public static void main(String[] args) {
        index = 1;
        showFiles("F:\\ChromeDownload\\佛爷\\（免费）巴比伦网站全部资源\\动画解压");
//        File file = new File("F:\\ChromeDownload\\佛爷\\（免费）巴比伦网站全部资源\\动画解压\\(同人アニメ) [180926][RJ235468][Ivory Tower] 里菜と穴(りなとあな)");
//        for (File file1 : file.listFiles()) {
//            System.out.println(file1.getAbsolutePath());
//        }
    }
}
