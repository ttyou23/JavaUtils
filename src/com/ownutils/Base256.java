package com.ownutils;

public class Base256 {

    private static final String BASE_CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+0123456789亡门义斤爪反介叮电号劝双书幻玉刊示末未击打比互切瓦止少日中不太巧正扑扒功扔去甘内水见午牛手毛气升长子卫乏公仓月氏勿欠田由史只央兄叼叫另叨叹四生失禾丘付仗了力乃刀又三父北占业旧帅归且旦目叶甲申仁什片仆化仇币代仙们仪白仔他斥瓜乎丛令用甩冈贝从今凶分允予云扎艺木五支厅印川亿个勺久凡及夕丸么广历尤友匹车巨牙屯犬区一乙二十丁厂七卜人入八九几儿仍仅飞刃习叉马乡丰王井开夫天无元专世古节本术可丙左厉右石布龙平灭轧东卡风丹匀乌凤勾文六方火为斗忆订计户认心尺引丑巴孔队办以也女之尸弓己已于干亏士工土才寸下大丈与万上小口巾山千乞";

    public static String encode(String srcStr) throws Exception {
        //有效值检查
        if(srcStr == null || srcStr.length() == 0) {
            return srcStr;
        }
        byte[] bytes= srcStr.getBytes("GBK");
        StringBuilder binStrB= new StringBuilder();
        for(int i= 0; i< bytes.length; i++) {
            int index = bytes[ i ] & 0xFF;
            binStrB.append(BASE_CODE.charAt(index));
        }

        return String.valueOf(binStrB);
    }

    public static String decode(String srcStr) throws Exception {
        //有效值检查
        if(srcStr == null || srcStr.length() == 0) {
            return srcStr;
        }

        //跟据Base64编码表将密文（Encoding）转为对应Value，然后转为二进制位字串
        char[] srcStrCh= srcStr.toCharArray();
        byte[] bytesDecode = new byte[srcStrCh.length];
        for(int i= 0; i< srcStrCh.length; i++) {
            int index = BASE_CODE.indexOf(srcStrCh[i]);
            bytesDecode[i] = (byte) (index | 0xFFFFFF00);
        }

        return new String(bytesDecode, "GBK");
    }

    public static void main(String[] args) {

        try {
            Base256 se=new Base256();
            String data = "的房价111";
            byte[] bytes = data.getBytes("GBK");
            System.out.println(data.length());
            System.out.println(bytes.length);


            String encode = se.encode(data);
            System.out.println(encode);
            String decode = se.decode(encode);
            System.out.println(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
