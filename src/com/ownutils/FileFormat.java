package com.ownutils;


import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FileFormat {

    public static final String ROOT_PATH = "D:\\test\\dos\\新建文件夹";

    public static final int DEFAULT_SWITCH_SIZE = 1888;
    public static final String ENCODE_TAG = ".se";

    private Base256 base256 = null;


    public FileFormat() {
        this.base256 = new Base256();
    }

    public void list_file(String root_path, boolean isEncode){

        File filelist = new File(root_path);
        if (filelist != null && filelist.listFiles().length > 0){

            List<EncodeFile> encodeFileList = new ArrayList<>();
            for (File file :filelist.listFiles()){
                EncodeFile encodeFile = new EncodeFile();
                encodeFile.setName(file.getName());
                encodeFile.setPath(file.getParent());
                encodeFile.setFullpath(file.getAbsolutePath());
                encodeFileList.add(encodeFile);
            }

            for(int i=0; i<encodeFileList.size(); i++){
                if (isEncode){
                    encodeFile(encodeFileList.get(i), i+1);
                } else {
                    decodeFile(encodeFileList.get(i), i+1);
                }

            }
        }
    }

    private boolean interchangeFile(EncodeFile file){

        try {
            //交换文件头尾内容
            RandomAccessFile frandom = new RandomAccessFile(file.getFullpath(), "rw");
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



    public void encodeFile(EncodeFile file, int index){

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

                String newPath = file.getPath() + File.separator + newFileName ;
                boolean result =  new File(file.fullpath).renameTo(new File(newPath));
                System.out.println(result + " ==》 " + file.getName());
                System.out.println("\r\n");
                if(!result){
                    interchangeFile(file);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


    public void decodeFile(EncodeFile file, int index){

        if (!file.getName().endsWith(ENCODE_TAG) || file.getName().length() < 8){
            System.out.println("falsefile: " + file.getName());
            return;
        }

        if(interchangeFile(file)){

            try {
                String newFileName = base256.decode(file.getName().replaceAll(ENCODE_TAG, "").substring(4));
                System.out.println(file.getName());
                System.out.println("newFileName: " + newFileName);

                String newPath = file.getPath() + File.separator + newFileName;
                boolean result =  new File(file.fullpath).renameTo(new File(newPath));
                System.out.println(result + " --> " + file.getName());
                System.out.println("\r\n");
                if(!result){
                    interchangeFile(file);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    private class EncodeFile{
        String name;
        String path;
        String fullpath;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getFullpath() {
            return fullpath;
        }

        public void setFullpath(String fullpath) {
            this.fullpath = fullpath;
        }
    }

    public static void main(String[] args) {

        FileFormat fileFormat = new FileFormat();
        fileFormat.list_file(ROOT_PATH, true);

//        System.out.println(String.format("%6d",12).replace(" ", "0"));
    	}

}
