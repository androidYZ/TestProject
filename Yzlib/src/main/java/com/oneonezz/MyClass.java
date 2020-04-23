package com.oneonezz;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.text.NumberFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("T.T->main: ");
    }

    public static void generateXML() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        for (int i = 16; i <= 80; i++) {
            String newFont = nf.format(i/2.0);
            String ss = "<dimen name=\"font_" +i+ "\">"+newFont+"sp</dimen>";
            System.out.println(ss);
        }
    }

    public static String getPath() {
        URL url = MyClass.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;
        try {
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("NiYouXi->getPath: " + filePath);
        if (filePath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"
            // 截取路径中的jar包名
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }

        File file = new File(filePath);
        filePath = file.getAbsolutePath();//得到windows下的正确路径
        return filePath;
    }

    public static void renameFolder(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        renameFolder(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());

                        if (file2.getName().endsWith(".txt")) {
                            String name = file2.getAbsolutePath();
                            System.out.println("NiYouXi->renameFolder: ");
                            boolean isOk = file2.renameTo(new File(name.substring(0,name.indexOf(".txt"))));
                            System.out.println("NiYouXi->traverseFolder2:删除 " + name + isOk);
                        }

//                        file2.renameTo(new File(file2.getAbsolutePath().replace("@2x", "")));
//                        file2.renameTo(new File(file2.getAbsolutePath().replace("@3x", "")));
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static void traverseFolder2(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());

                        if (file2.getName().contains("@3x.png")) {
                            String name = file2.getName();
                            boolean temp = file2.delete();
                            System.out.println("NiYouXi->traverseFolder2:删除 " + name + temp);
                        }

//                        file2.renameTo(new File(file2.getAbsolutePath().replace("@2x", "")));
//                        file2.renameTo(new File(file2.getAbsolutePath().replace("@3x", "")));
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    /**
     * 生成xml方法
     */
    public static void createXml() {
        try {
// 创建解析器工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document document = db.newDocument();
// 不显示standalone="no"
            document.setXmlStandalone(true);
            Element bookstore = document.createElement("bookstore");
// 向bookstore根节点中添加子节点book
            Element book = document.createElement("book");

            Element name = document.createElement("name");
// 不显示内容 name.setNodeValue("不好使");
            name.setTextContent("雷神");
            book.appendChild(name);
// 为book节点添加属性
            book.setAttribute("id", "1");
// 将book节点添加到bookstore根节点中
            bookstore.appendChild(book);
// 将bookstore节点（已包含book）添加到dom树中
            document.appendChild(bookstore);

// 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();
// 创建 Transformer对象
            Transformer tf = tff.newTransformer();

// 输出内容是否使用换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
// 创建xml文件并写入内容
            tf.transform(new DOMSource(document), new StreamResult(new File("book1.xml")));
            System.out.println("book1.xml ok");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("book1.xml fail");
        }
    }
}
