package com.ownutils;

import java.io.UnsupportedEncodingException;

public class Base64 {

    //Constructor
    public Base64() {

    }

    private static final String base64Code= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";


    public static String encode_GBK(String srcStr){

        try {
            byte[] tempBytes = srcStr.getBytes("GBK");
            return encode(tempBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode_UTF8(String srcStr){

        try {
            byte[] tempBytes = srcStr.getBytes("utf-8");
            return encode(tempBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode(byte[] srcStrByte) {
        //有效值检查
        if(srcStrByte == null || srcStrByte.length == 0) {
            return null;
        }

        StringBuilder encodeStr= new StringBuilder();

        int re = srcStrByte.length % 3;
        int encode_len = 0;
        if(re ==0){
            encode_len = srcStrByte.length/3;
        } else {
            encode_len = srcStrByte.length/3+1;
        }

        byte[] tempBytes = new byte[3*encode_len];
        System.arraycopy(srcStrByte, 0, tempBytes, 0, srcStrByte.length);

        for(int i= 0; i < encode_len; i++) {

            int c = (tempBytes[3*i]&0xFF)<<16 | (tempBytes[3*i+1]&0xFF)<<8 | (tempBytes[3*i+2]&0xFF);

            encodeStr.append(base64Code.charAt(c >> 18 & 0x3F));
            encodeStr.append(base64Code.charAt(c >> 12 & 0x3F));
            encodeStr.append(base64Code.charAt(c >> 6 & 0x3F));
            encodeStr.append(base64Code.charAt(c & 0x3F));
        }

        char[] encodeChar = encodeStr.toString().toCharArray();

        if(re == 1){
            encodeChar[encodeChar.length -1] = '=';
            encodeChar[encodeChar.length -2] = '=';
        } else if (re == 2){
            encodeChar[encodeChar.length -1] = '=';
        }
        return new String(encodeChar);
    }


    public static String decode_utf8(String srcStr){

        try {
            return new String(decode(srcStr), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode_gbk(String srcStr){

        try {
            return new String(decode(srcStr), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] decode(String srcStr) {
        //有效值检查
        if(srcStr == null || srcStr.length() == 0) {
            return null;
        }
        //检测密文中“=”的个数后将之删除，同时删除换行符
        int eqCounter= 0;
        if(srcStr.endsWith("==")) {
            eqCounter= 2;
        } else if(srcStr.endsWith("=")) {
            eqCounter= 1;
        }
        srcStr= srcStr.replaceAll("=", "");
        srcStr= srcStr.replaceAll("\r\n", "");
        //跟据Base64编码表将密文（Encoding）转为对应Value，然后转为二进制位字串
        char[] srcStrCh= srcStr.toCharArray();
        StringBuilder indexBinStr= new StringBuilder();

        String indexBin= null;
        for(int i= 0; i< srcStrCh.length; i++) {
            indexBin= Integer.toBinaryString(base64Code.indexOf((byte)srcStrCh[i]));
            while(indexBin.length()< 6) {
                indexBin= "0"+ indexBin;
            }
            indexBinStr.append(indexBin);
        }
        //删除因编码而在尾部补位的“0”后得到明文的ASCII码的二进制位字串
        if(eqCounter == 1) {
            indexBinStr.delete(indexBinStr.length()- 2, indexBinStr.length());
        } else if(eqCounter == 2) {
            indexBinStr.delete(indexBinStr.length()- 4, indexBinStr.length());
        }
        String asciiBinStr= String.valueOf(indexBinStr);
        //将上面得到的二进制位字串分隔成字节后还原成明文
        String asciiBin= null;
        byte[] ascii= new byte[asciiBinStr.length()/ 8];
        for(int i= 0; i< ascii.length; i++) {
            asciiBin= asciiBinStr.substring(0, 8);
            asciiBinStr= asciiBinStr.substring(8);
            ascii[i]= (byte) Integer.parseInt(asciiBin, 2);
        }
        return ascii;
    }

    public static byte[] decode1(String srcStr){
        //有效值检查
        if(srcStr == null || srcStr.length() == 0) {
            return null;
        }
        //检测密文中“=”的个数后将之删除，同时删除换行符
        int eqCounter= 0;
        if(srcStr.endsWith("==")) {
            eqCounter= 2;
        } else if(srcStr.endsWith("=")) {
            eqCounter= 1;
        }
        srcStr= srcStr.replaceAll("=", "");
        srcStr= srcStr.replaceAll("\r\n", "");
        //跟据Base64编码表将密文（Encoding）转为对应Value，然后转为二进制位字串
        char[] srcStrCh= srcStr.toCharArray();
        StringBuilder indexBinStr= new StringBuilder();



//
//        for(int i= 0; i< srcStrCh.length; i++) {
//            int b = base64Code.indexOf((byte)srcStrCh[i]);
//
//            indexBin= Integer.toBinaryString();
//            while(indexBin.length()< 6) {
//                indexBin= "0"+ indexBin;
//            }
//            indexBinStr.append(indexBin);
//        }
        return null;
    }



    public static void main(String[] args) {

        String data = "DFADSFADSFFASF代理费打飞机2232323";
        byte[] temp = null;
        try {
            temp = data.getBytes("utf-8");
            String encode = encode(temp);
            System.out.println(encode);
            System.out.println(decode_utf8(encode));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
