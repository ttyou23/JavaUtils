package com.ownutils;


import java.io.File;
import java.io.RandomAccessFile;

public class FileFormat {

    public static final String ROOT_PATH = "D:\\test\\dos\\新建文件夹";

    public static final int DEFAULT_SWITCH_SIZE = 1888;
    public static final String CHARSETNAME = "GBK";
    public static final String ENCODE_TAG = ".se";

    private Base256 base256 = null;

    public FileFormat() {
        this.base256 = new Base256();
    }

    public void list_file(String root_path, boolean isEncode){

        File filelist = new File(root_path);
        if (filelist.listFiles().length > 0){
            for(int i=0; i<filelist.listFiles().length; i++){
                if (isEncode){
                    encodeFile(filelist.listFiles()[i], i);
                } else {
                    decodeFile(filelist.listFiles()[i], i);
                }
            }
        }
    }

    private boolean interchangeFile(File file){

        try {
            //交换文件头尾内容
            RandomAccessFile frandom = new RandomAccessFile(file, "rw");
            int switch_size = DEFAULT_SWITCH_SIZE;
            if(frandom.length() < 2*DEFAULT_SWITCH_SIZE){
                switch_size = (int)(frandom.length()/2);
            }

            System.out.println("switch_size: " + switch_size);
            byte[] head_bytes = new byte[switch_size];
            byte[] end_bytes = new byte[switch_size];

            frandom.seek(0);
            frandom.read(head_bytes);
            frandom.seek(frandom.length()-switch_size);
            frandom.read(end_bytes);
            frandom.seek(0);
            frandom.write(end_bytes);
            frandom.seek(frandom.length()-switch_size);
            frandom.write(head_bytes);
            frandom.close();
            return true;
        }catch (Exception e){
            e.getStackTrace();
        }
        return false;
    }



    public void encodeFile(File file, int index){

        if (file.getName().endsWith(ENCODE_TAG)){
            return;
        }

        if(interchangeFile(file)){
            try{

                //重命名文件
                String newFileName = base256.encode(file.getName());
                newFileName = String.format("%3d", index).replace(" ", "0") + "_" + newFileName + ENCODE_TAG;
                System.out.println(file.getName());
                System.out.println("newFileName: " + newFileName);

                String newPath = file.getParent() + File.separator + newFileName ;
                boolean result =  file.renameTo(new File(newPath));
                System.out.println(result + " ==》 " + file.getName());
                System.out.println("\r\n");

            }catch (Exception e){
                e.getStackTrace();
            }
        }
    }


    public void decodeFile(File file, int index){

        if (!file.getName().endsWith(ENCODE_TAG) || file.getName().length() < 8){
            return;
        }

        if(interchangeFile(file)){

            try {
                String newFileName = base256.decode(file.getName().replaceAll(ENCODE_TAG, "").substring(4));
                System.out.println(file.getName());
                System.out.println("newFileName: " + newFileName);

                String newPath = file.getParent() + File.separator + newFileName;
                boolean result =  file.renameTo(new File(newPath));
                System.out.println(result + " --> " + file.getName());
                System.out.println("\r\n");
            }catch (Exception e){
                e.getStackTrace();
            }
        }

    }


    public static void main(String[] args) {

        FileFormat fileFormat = new FileFormat();
        fileFormat.list_file(ROOT_PATH, true);

//        System.out.println(String.format("%6d",12).replace(" ", "0"));
    	}

}
