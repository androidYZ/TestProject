package com.oneonezz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by ${Justin} on 2019/10/28.
 */
public class TTclass {

    /**
     * 不写字第二天
     * 1，app难点
     * 我最终需要的是一个字对应的图片
     * 我传入的参数是？
     * 字和附加参数如类型
     * 所以我要通过字和参数找到一张图片
     * 那么需要什么样的数据结构呢？
     * 现在有图片和图片对应的文字
     * 所以文字按行来显示，11行是一张图片
     * 注意：1画等可以用特殊字符来代替
     * <p>
     * 还可以用单个图片的方法，如果不大的话
     * <p>
     * 所以现在第一步是
     * 把文字和图片对应起来，也就是写个文件就可以了，网上找图片识别
     * <p>
     * 第二步
     * 图片裁成一张张小图
     * <p>
     * 写个钩子先读SD卡
     */
    public static void main(String[] args) {
        /** 腾讯文字识别 */
//        traverseFolder2Net("C:\\Users\\Administrator\\Desktop\\ww");

        StringBuilder sb = new StringBuilder();
        sb.append("1234");
        sb.insert(1, "你");
        System.out.println("T.T->main: " + sb.toString() + "  " + sb.length());
    }


    private static void cutbigImg2Net(File inputFile, File outputFile) {
        try {
            BufferedImage bufImage = ImageIO.read(inputFile);
            BufferedImage subImag = bufImage.getSubimage(141, 313, bufImage.getWidth() - 138 * 2, bufImage.getHeight() - 314 - 465);
            boolean isOk = ImageIO.write(subImag, "png", outputFile);
            System.out.println("T.T->main: 成功" + isOk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void traverseFolder2Net(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2Net(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getName());
                        cutbigImg2Net(file2, new File("C:\\Users\\Administrator\\Desktop\\33", file2.getName()));
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }


    private static void traverseFolder(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getName());

                        cutbigImg(file2, new File("C:\\Users\\Administrator\\Desktop\\22", file2.getName()));
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }


    private static void cutbigImg(File inputFile, File outputFile) {
        try {
            BufferedImage bufImage = ImageIO.read(inputFile);
            BufferedImage subImag = bufImage.getSubimage(131, 303, bufImage.getWidth() - 130 * 2, bufImage.getHeight() - 304 - 435);
            boolean isOk = ImageIO.write(subImag, "png", outputFile);
            System.out.println("T.T->main: 成功" + isOk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
