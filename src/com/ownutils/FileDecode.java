package com.ownutils;

public class FileDecode {

    public static final String ROOT_PATH = "D:\\test\\dos\\新建文件夹";

    public static final String WORK_PATH = "F:\\upload\\encode";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(" ===================================================开始=========================================================================");
        FileFormat fileFormat = new FileFormat();
        fileFormat.list_file(WORK_PATH, false);
        long end = System.currentTimeMillis();
        System.out.println(" ===================================================结束=========================================================================");
        System.out.println("耗时：" + (end-start));
    	}
}
