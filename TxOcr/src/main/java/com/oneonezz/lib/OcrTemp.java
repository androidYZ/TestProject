package com.oneonezz.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Created by ${Justin} on 2020/5/11.
 */
public class OcrTemp {

    private static String filePath = "D:\\workspace_as\\YzStart2\\YzNote\\font_tyz.txt";
    private static StringBuffer sb = new StringBuffer();


    public static void main(String[] args) throws IOException {
        
        System.out.println("T.T->main: start-------------------------------------------");
        
        /** 读取txt文件 */
        File file = new File(filePath);
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineText = null;
        StringBuilder sbFont = new StringBuilder();
        while ((lineText = bufferedReader.readLine()) != null) {
            sbFont.append(lineText);
        }
        read.close();

        String needFont = sbFont.toString();

        for (int i = 0; i <= 9; i++) {
            needFont = needFont.replace(i + "", "");
        }

        needFont = needFont.replace("-------", "");
        needFont = needFont.replace(System.lineSeparator(),"");
        
        System.out.println("T.T->main: "+needFont.length());
        System.out.println("T.T->main: "+(12*11*53+11*2+10));

    }

    private static void ocrTemp() {
        String ss = "努沤呕怄判彷抛刨沛批邳\n" +
                "伾纰屁评钋杯沏圻芪岐杞\n" +
                "启弃汽岍佥芡羌抢呛芹\n" +
                "芩吣沁穷邱求虬岖诎驱劬\n" +
                "却扰忍韧轫饪妊妊芮讷闰\n" +
                "沙纱芟杉删杓邵劭佘社伸\n" +
                "身沈声时识豕寿抒纾束吮\n" +
                "私兕伺祀姒忪宋苏诉邰呔\n" +
                "汰坍坛忐忑体条听佟彤投\n" +
                "秃抟忒吞囤饨佗陀妥完汪\n" +
                "忘违围帏闱沩苇尾纬位纹\n" +
                "吻汶我肟沃巫呜芜吾吴迕庑";

        ss = ss.replace("\n", "");
        StringBuilder temp = new StringBuilder();
        temp.append(ss);
        for (int i = 11, j = 0; i <= ss.length(); i += 12, j++) {
            temp.insert(i + j, System.lineSeparator());
        }
        System.out.println(temp.toString());


//        int i=0;
//        for (char c = 0x4E00; c <= 0x9FA5; c++) {
//            i++;
//            System.out.println("T.T->main: " + (((int) c + "," + c)));
//        }
//        System.out.println("T.T->main: "+i);
    }
}
