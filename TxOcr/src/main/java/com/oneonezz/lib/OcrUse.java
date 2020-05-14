package com.oneonezz.lib;

import com.google.gson.Gson;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.GeneralAccurateOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralAccurateOCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;

public class OcrUse {


    private static String filePath = "D:\\workspace_as\\YzStart2\\YzNote\\font_tyz.txt";
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
//        System.out.println("T.T->main: start");
        /** 整个文件夹 */
//        traverseFolder("C:\\Users\\Administrator\\Desktop\\4444");
//        saveString2File();

        /** 识别单个图片 */
//        imgOcr(new File("C:\\Users\\Administrator\\Desktop\\22_new\\00.png"));
//        imgOcrGenera(new File("C:\\Users\\Administrator\\Desktop\\4444\\font  1.png"));
//        saveString2File();

        /** 修改图片颜色 */
//        changeImgColor(new File("C:\\Users\\Administrator\\Desktop\\4444\\font  1.png"));

        /** 重命名 */
//        String tt = "恬0.png";
//        System.out.println("T.T->main: "+tt.length() + tt.replace("0",""));
//        traverseFolder("C:\\Users\\Administrator\\Desktop\\22_temp2", new OnTraverse() {
//            @Override
//            public void onTraverse(File file) {
//                file.renameTo(new File(file.getParent(), file.getName().replace("0","")));
//            }
//        });


        /** 裁剪图片 */
//        File inputFile = new File("C:\\Users\\Administrator\\Desktop\\22\\font  (1).png");
//        try {
//            cutSmallImg(inputFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /** 缩放图片 文件夹 */
//        try {
//            Thumbnails.of(new File("C:\\Users\\Administrator\\Desktop\\4444").listFiles()).size(720, 887).outputFormat("png").toFiles(Rename.NO_CHANGE);
//            Thumbnails.of(new File("C:\\Users\\Administrator\\Desktop\\small").listFiles()).scale(0.5).outputFormat("png").toFiles(Rename.NO_CHANGE);
//            System.out.println("T.T->main: ok");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        /** 缩放图片 单个文件 */
//        try {
//            BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\4444\\font  2.png"));
//            BufferedImage thumbnail = Thumbnails.of(originalImage).scale(0.35f).asBufferedImage();
//            ImageIO.write(thumbnail, "png", new File("photo/huhx5.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /** 读取txt文件 */
//        File file = new File(filePath);
//        InputStreamReader read = new InputStreamReader(
//                new FileInputStream(file), StandardCharsets.UTF_8);//考虑到编码格式
//        BufferedReader bufferedReader = new BufferedReader(read);
//        String lineText = null;
//        StringBuilder sbFont = new StringBuilder();
//        while ((lineText = bufferedReader.readLine()) != null) {
//            sbFont.append(lineText);
//        }
//        read.close();
//
//        String needFont = sbFont.toString();
//
//        for (int i = 1; i <= 36; i++) {
//            needFont = needFont.replace(i + "画", "_");
//        }
//
//        for (int i = 0; i <= 9; i++) {
//            needFont = needFont.replace(i + "", "");
//        }
//        needFont = needFont.replace(":", "");
//        needFont = needFont.replace(" ", "");
//        needFont = needFont.replace("，", "");
//        needFont = needFont.replace("|", "");
//        needFont = needFont.replace("、", "");
//        needFont = needFont.replace(".", "");
//        needFont = needFont.replace("!", "");
//        needFont = needFont.replace(System.lineSeparator(), "");
//
//
//        String[] arr = needFont.split("-------");
//
//        for(int i=0; i<arr.length; i++){
//            arr[i] = arr[i].replace("-", "");
//        }
//
//        int mCount = 2;
//        for (String sT : arr) {
//
//            StringBuilder temp = new StringBuilder();
//            temp.append(sT);
//            for (int i = 11, j = 0; i <= sT.length(); i += 12, j++) {
//                temp.insert(i + j, System.lineSeparator());
//            }
//            sb.append(temp.toString())
//                    .append(System.lineSeparator())
//                    .append("-------" + mCount)
//                    .append(System.lineSeparator());
//            mCount++;
//        }
//        System.out.println(sb.toString());

        /** 保存String到文件 */
//        System.out.println("T.T->main: ok");
//        saveString2File();
    }

    public static String StringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        // String   regEx  =  "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    private static void cupBigImg(File inputFile, File outputFile, int height) {
        try {
            BufferedImage bufImage = ImageIO.read(inputFile);
            BufferedImage subImag = bufImage.getSubimage(0, height, bufImage.getWidth(), bufImage.getHeight() - height);
            boolean isOk = ImageIO.write(subImag, "png", outputFile);
            System.out.println("T.T->main: 成功" + isOk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int countImg = 1;

    private static void cutSmallImg(File inputFile) throws IOException {
        BufferedImage bufImage = ImageIO.read(inputFile);
        float width = (float) (bufImage.getWidth() / 11.0);
        float height = (float) (bufImage.getHeight() / 12.0);


        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 11; j++) {

                int startLeft = Math.round(j * width);
                int startTop = Math.round(i * height);

                int width_user = Math.round(width) + 6;
                int height_user = Math.round(height) - 18;

                if (j != 0) { //向左移3个像素
                    startLeft -= 2;
                }

                if (i != 0) {//向上移3个像素
                    startTop -= 3;
                }

                if ((startLeft + width_user) > bufImage.getWidth()) {
                    width_user = bufImage.getWidth() - startLeft;
                }
                if ((startTop + height_user) > bufImage.getHeight()) {
                    height_user = bufImage.getHeight() - startTop;
                }

                BufferedImage subImag = bufImage.getSubimage(startLeft, startTop, width_user, height_user);
                String tempFileName = getWord(countImg) + ".png";
                File ouputFileImg = new File("C:\\Users\\Administrator\\Desktop\\22_new", tempFileName);
                if (ouputFileImg.exists()) {
                    ouputFileImg = new File("C:\\Users\\Administrator\\Desktop\\22_new", countImg + tempFileName);
                }
                boolean isOk = ImageIO.write(subImag, "png", ouputFileImg);

                System.out.println("T.T->main: 成功" + isOk + " : " + countImg + " : " + ouputFileImg.getName());
                countImg++;
            }
        }

    }


    public static String needFont;

    public static String getWord(int count) throws IOException {

        if (needFont != null) {
            return String.valueOf(needFont.charAt(count - 1));
        }
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

        needFont = sbFont.toString();

        for (int i = 0; i <= 9; i++) {
            needFont = needFont.replace(i + "", "");
        }
        needFont = needFont.replace("-------", "");
        needFont = needFont.replace(System.lineSeparator(), "");
        return String.valueOf(needFont.charAt(count - 1));
    }

    private static void saveString2File() {
        FileWriter fwriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fwriter = new FileWriter(filePath, false);
            fwriter.write(sb.toString());

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void traverseFolder(String path, OnTraverse... onTraverses) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        if (onTraverses != null && onTraverses.length > 0) {
                            onTraverses[0].onTraverse(file2);
                        } else {
//                            imgOcr(file2);
                            imgOcrGenera(file2);
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    private static void imgOcrGenera(File file2) {

        try {

            Credential cred = new Credential("AKID2A1d5eKsa1RbiXKWgxwdDWXGsIbcnO3Q", "GPIrACI7eu61ipfHwmLcKnysgDqCHFz0");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            OcrClient client = new OcrClient(cred, "ap-guangzhou", clientProfile);

            String base64Img = GetImageStr(file2.getAbsolutePath());

            String params = "{\"ImageBase64\":\"" + base64Img + "\"}";

            GeneralBasicOCRRequest req = GeneralBasicOCRRequest.fromJsonString(params, GeneralBasicOCRRequest.class);

            GeneralBasicOCRResponse resp = client.GeneralBasicOCR(req);

            String jj = GeneralBasicOCRRequest.toJsonString(resp);

            Gson gson = new Gson();
            OcrBean bean = gson.fromJson(jj, OcrBean.class);
            List<OcrBean.TextDetectionsBean> textDetertions = bean.getTextDetections();
            for (OcrBean.TextDetectionsBean beanTemp : textDetertions) {
                sb.append(beanTemp.getDetectedText());
                sb.append(System.getProperty("line.separator"));
            }
            sb.append("-------");
            sb.append(System.getProperty("line.separator"));

            Thread.sleep(200);

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void imgOcr(File file2) {
        try {

            Credential cred = new Credential("AKID2A1d5eKsa1RbiXKWgxwdDWXGsIbcnO3Q", "GPIrACI7eu61ipfHwmLcKnysgDqCHFz0");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            OcrClient client = new OcrClient(cred, "ap-guangzhou", clientProfile);

            String base64Img = GetImageStr(file2.getAbsolutePath());

            String params = "{\"ImageBase64\":\"" + base64Img + "\"}";
            GeneralAccurateOCRRequest req = GeneralAccurateOCRRequest.fromJsonString(params, GeneralAccurateOCRRequest.class);

            GeneralAccurateOCRResponse resp = client.GeneralAccurateOCR(req);

            String jj = GeneralAccurateOCRRequest.toJsonString(resp);


            Gson gson = new Gson();
            OcrBean bean = gson.fromJson(jj, OcrBean.class);
            List<OcrBean.TextDetectionsBean> textDetertions = bean.getTextDetections();
            for (OcrBean.TextDetectionsBean beanTemp : textDetertions) {
                sb.append(beanTemp.getDetectedText());
            }
            sb.append(System.getProperty("line.separator"));
            sb.append("-------");
            sb.append(System.getProperty("line.separator"));

            System.out.println("------> " + jj);

            Thread.sleep(200);

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String GetImageStr(String imgFile) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    public static void changeImgColor(File inputFile) throws IOException {
        BufferedImage bufImage = ImageIO.read(inputFile);
        float width = (float) (bufImage.getWidth() / 11.0);
        float height = (float) (bufImage.getHeight() / 12.0);


        bufImage.setRGB(10, 10, 0xFF0000);

        int[] ImageArrayOne = new int[100 * 10];
        Arrays.fill(ImageArrayOne, 0xFFFFFF);

        bufImage.setRGB(0, 0, 100, 10, ImageArrayOne, 0, 100);

        boolean isOk = ImageIO.write(bufImage, "png", new File("C:\\Users\\Administrator\\Desktop\\tt.png"));
    }


    public static BufferedImage mergeImage(BufferedImage img1, BufferedImage img2,
                                           boolean isHorizontal) throws IOException {
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();
        int w2 = img2.getWidth();
        int h2 = img2.getHeight();

        // 从图片中读取RGB
        int[] ImageArrayOne = new int[w1 * h1];
        ImageArrayOne = img1.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
        int[] ImageArrayTwo = new int[w2 * h2];
        ImageArrayTwo = img2.getRGB(0, 0, w2, h2, ImageArrayTwo, 0, w2);

        // 生成新图片
        BufferedImage DestImage = null;
        if (isHorizontal) { // 水平方向合并
            Graphics2D g2d = null;
            if (h1 >= h2) {
                DestImage = new BufferedImage(w1 + w2, h1, BufferedImage.TYPE_INT_RGB);
                g2d = DestImage.createGraphics();
                g2d.setPaint(Color.WHITE);
                g2d.fillRect(0, 0, w1 + w2, h1);
                g2d.dispose();
            } else {
                DestImage = new BufferedImage(w2, h1, BufferedImage.TYPE_INT_RGB);//TYPE_INT_RGB
                g2d = DestImage.createGraphics();
                g2d.setPaint(Color.WHITE);
                g2d.fillRect(0, 0, w2 + w1, h1);
                g2d.dispose();
            }
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            DestImage.setRGB(w1, 0, w2, h2, ImageArrayTwo, 0, w2);

        } else { // 垂直方向合并
            Graphics2D g2d = null;
            if (w1 >= w2) {
                DestImage = new BufferedImage(w1, h1 + h2, BufferedImage.TYPE_INT_RGB);//TYPE_INT_RGB
                g2d = DestImage.createGraphics();
                g2d.setPaint(Color.WHITE);
                g2d.fillRect(0, 0, w1 + w2, h1 + h2);
                g2d.dispose();
            } else {
                DestImage = new BufferedImage(w2, h1 + h2, BufferedImage.TYPE_INT_RGB);//TYPE_INT_RGB
                g2d = DestImage.createGraphics();
                g2d.setPaint(Color.WHITE);
                g2d.fillRect(0, 0, w2 + w1, h1 + h2);
                g2d.dispose();
            }
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            DestImage.setRGB(0, h1, w2, h2, ImageArrayTwo, 0, w2); // 设置下半部分的RGB
        }
        return DestImage;
    }

}
