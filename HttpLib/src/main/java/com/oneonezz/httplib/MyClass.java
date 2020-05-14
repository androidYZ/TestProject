package com.oneonezz.httplib;

import com.qcloud.image.ImageClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import sun.misc.BASE64Encoder;

public class MyClass {


    public static void main(String[] args) throws Exception {
        System.out.println("T.T->main: ");
//        codeTest();
        System.out.println("T.T->main: ok    ");

    }

    /**
     * 通用印刷体OCR
     */
    private static void ocrGeneral(ImageClient imageClient, String bucketName) {

    }


    /**
     * https://cvm.tencentcloudapi.com/
     * <p>
     * Authorization: TC3-HMAC-SHA256 Credential=AKIDEXAMPLE/2018-05-30/cvm/tc3_request, SignedHeaders=content-type;host, Signature=582c400e06b5924a6f2b5d7d672d79c15b13162d9279b0855cfba6789a8edb4c
     * Content-Type : application/json
     * Host: cvm.tencentcloudapi.com
     * X-TC-Action: DescribeInstances
     * X-TC-Version: 2017-03-12
     * X-TC-Timestamp: 1527672334
     * X-TC-Region: ap-guangzhou
     * <p>
     * {"Offset":0,"Limit":10}
     */

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static void codeTest() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());//创建拦截对象

        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这一句一定要记得写，否则没有数据输出


        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
//                .addNetworkInterceptor(logInterceptor)  //设置打印拦截日志
                .connectTimeout(60, TimeUnit.SECONDS).build();

        FormBody formBody = null;
        try {

            String t = "你";
            String utf8 = new String(t.getBytes("UTF-8"));
            System.out.println(utf8);
            String unicode = new String(utf8.getBytes(), "UTF-8");
            System.out.println(unicode);
            String gbk = new String(unicode.getBytes("GBK"));
            System.out.println(gbk);

            formBody = new FormBody.Builder()
                    .add("is_zb", "B7B1")
                    .build();
        } catch (UnsupportedEncodingException e) {
            System.out.println("T.T->codeTest: 异常");
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=gb2312"),
                "is_zb=你好啊");

        Request request = new Request.Builder()
                .post(body)
                .url("http://www.jiantizi.com/help/bihua.asp")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.out.println("T.T->baidu: 失败");
                return;
            }
            assert response.body() != null;
            System.out.println("T.T->codeTest: ");
            System.out.println("body____________ " + new String(response.body().bytes(), "GBK"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void baidu() throws Exception {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());//创建拦截对象

        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这一句一定要记得写，否则没有数据输出


        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(logInterceptor)  //设置打印拦截日志
                .connectTimeout(60, TimeUnit.SECONDS).build();

        String base64Img = GetImageStr("C:\\Users\\Administrator\\Desktop\\ww\\w.png");


        String json = "{\"Offset\":0,\"Limit\":10,\"Action\":\"GeneralFastcvm\"" +
                ",\"Action\":\"GeneralFastcvm\"" +
                ",\"Region\":\"ap-guangzhou\"" +
//                ",\"ImageBase64\":\"GeneralFastcvm\"" +
                ",\"Version\":\"2018-11-19\"}";
        RequestBody body = RequestBody.create(JSON, json);

//        RequestBody body = new FormBody.Builder()
//                .add("Action", "GeneralFastcvm")
//                .add("Version", "2018-11-19")
//                .add("Region", "ap-guangzhou")
//                .add("ImageBase64", base64Img)
//                .build();

        String time = System.currentTimeMillis() / 1000 + "";
        String authori = getAuthorization(time);


        Request request = new Request.Builder()
                .post(body)
                .url("https://cvm.tencentcloudapi.com/")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Authorization", "TC3-HMAC-SHA256 Credential=AKID2A1d5eKsa1RbiXKWgxwdDWXGsIbcnO3Q/2020-05-08/ocr/tc3_request, SignedHeaders=content-type;host,Signature=30373c884a2b285d0dd12f053c43ab3b3255269ca320c036c443b20fce76e309")
                .addHeader("Host", "cvm.tencentcloudapi.com")
                .addHeader("X-TC-Action", "DescribeInstances")
                .addHeader("X-TC-Version", "2017-03-12")
                .addHeader("X-TC-Timestamp", time + "000")
                .addHeader("X-TC-Region", "ap-guangzhou")
//                .addHeader("X-TC-Token", "ap-guangzhou")

                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.out.println("T.T->baidu: 失败");
                return;
            }
            assert response.body() != null;
            System.out.println("body " + response.body().string());
            System.out.println("Vary: " + response.message());

        } catch (IOException e) {
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

    private static void sendGet() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("cvm.tencentcloudapi.com")
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("T.T->main: " + response.message());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        System.out.println(response.body().string());
    }

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");


    public void run2() throws Exception {
        String postBody = "";

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
    }

    public static final MediaType MEDIA_TYPE_MARKDOWN2
            = MediaType.parse("text/x-markdown; charset=utf-8");


    public void run4() throws Exception {
        File file = new File("README.md");

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN2, file))
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
    }

    private final static Charset UTF8 = StandardCharsets.UTF_8;
    private final static String SECRET_ID = "AKID2A1d5eKsa1RbiXKWgxwdDWXGsIbcnO3Q";
    private final static String SECRET_KEY = "GPIrACI7eu61ipfHwmLcKnysgDqCHFz0";
    private final static String CT_JSON = "application/json; charset=utf-8";

    public static byte[] hmac256(byte[] key, String msg) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(msg.getBytes(UTF8));
    }

    public static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(UTF8));
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }

    private static String getAuthorization(String time) throws Exception {
        String service = "cvm";
        String host = "cvm.tencentcloudapi.com";
        String region = "ap-guangzhou";
        String action = "DescribeInstances";
        String version = "2017-03-12";
        String algorithm = "TC3-HMAC-SHA256";
        String timestamp = time;

        //String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 注意时区，否则容易出错
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = sdf.format(new Date(Long.valueOf(timestamp + "000")));


        // ************* 步骤 1：拼接规范请求串 *************
        String httpRequestMethod = "POST";
        String canonicalUri = "/";
        String canonicalQueryString = "";
        String canonicalHeaders = "content-type:application/json; charset=utf-8\n" + "host:" + host + "\n";
        String signedHeaders = "content-type;host";

        String payload = "{\"Limit\": 1, \"Filters\": [{\"Values\": [\"\\u672a\\u547d\\u540d\"], \"Name\": \"instance-name\"}]}";
        String hashedRequestPayload = sha256Hex(payload);
        String canonicalRequest = httpRequestMethod + "\n" + canonicalUri + "\n" + canonicalQueryString + "\n"
                + canonicalHeaders + "\n" + signedHeaders + "\n" + hashedRequestPayload;

        // ************* 步骤 2：拼接待签名字符串 *************
        String credentialScope = date + "/" + service + "/" + "tc3_request";
        String hashedCanonicalRequest = sha256Hex(canonicalRequest);
        String stringToSign = algorithm + "\n" + timestamp + "\n" + credentialScope + "\n" + hashedCanonicalRequest;

        // ************* 步骤 3：计算签名 *************
        byte[] secretDate = hmac256(("TC3" + SECRET_KEY).getBytes(UTF8), date);
        byte[] secretService = hmac256(secretDate, service);
        byte[] secretSigning = hmac256(secretService, "tc3_request");
        String signature = DatatypeConverter.printHexBinary(hmac256(secretSigning, stringToSign)).toLowerCase();

        // ************* 步骤 4：拼接 Authorization *************
        String authorization = algorithm + " " + "Credential=" + SECRET_ID + "/" + credentialScope + ", "
                + "SignedHeaders=" + signedHeaders + ", " + "Signature=" + signature;
        return authorization;
    }


    public static void mainss(String[] args) throws Exception {
        String service = "cvm";
        String host = "cvm.tencentcloudapi.com";
        String region = "ap-guangzhou";
        String action = "DescribeInstances";
        String version = "2017-03-12";
        String algorithm = "TC3-HMAC-SHA256";
        String timestamp = "1551113065";
        //String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 注意时区，否则容易出错
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = sdf.format(new Date(Long.valueOf(timestamp + "000")));

        // ************* 步骤 1：拼接规范请求串 *************
        String httpRequestMethod = "POST";
        String canonicalUri = "/";
        String canonicalQueryString = "";
        String canonicalHeaders = "content-type:application/json; charset=utf-8\n" + "host:" + host + "\n";
        String signedHeaders = "content-type;host";

        String payload = "{\"Limit\": 1, \"Filters\": [{\"Values\": [\"\\u672a\\u547d\\u540d\"], \"Name\": \"instance-name\"}]}";
        String hashedRequestPayload = sha256Hex(payload);
        String canonicalRequest = httpRequestMethod + "\n" + canonicalUri + "\n" + canonicalQueryString + "\n"
                + canonicalHeaders + "\n" + signedHeaders + "\n" + hashedRequestPayload;
        System.out.println(canonicalRequest);

        // ************* 步骤 2：拼接待签名字符串 *************
        String credentialScope = date + "/" + service + "/" + "tc3_request";
        String hashedCanonicalRequest = sha256Hex(canonicalRequest);
        String stringToSign = algorithm + "\n" + timestamp + "\n" + credentialScope + "\n" + hashedCanonicalRequest;
        System.out.println(stringToSign);

        // ************* 步骤 3：计算签名 *************
        byte[] secretDate = hmac256(("TC3" + SECRET_KEY).getBytes(UTF8), date);
        byte[] secretService = hmac256(secretDate, service);
        byte[] secretSigning = hmac256(secretService, "tc3_request");
        String signature = DatatypeConverter.printHexBinary(hmac256(secretSigning, stringToSign)).toLowerCase();
        System.out.println(signature);

        // ************* 步骤 4：拼接 Authorization *************
        String authorization = algorithm + " " + "Credential=" + SECRET_ID + "/" + credentialScope + ", "
                + "SignedHeaders=" + signedHeaders + ", " + "Signature=" + signature;
        System.out.println(authorization);

        TreeMap<String, String> headers = new TreeMap<String, String>();
        headers.put("Authorization", authorization);
        headers.put("Content-Type", CT_JSON);
        headers.put("Host", host);
        headers.put("X-TC-Action", action);
        headers.put("X-TC-Timestamp", timestamp);
        headers.put("X-TC-Version", version);
        headers.put("X-TC-Region", region);

        StringBuilder sb = new StringBuilder();
        sb.append("curl -X POST https://").append(host)
                .append(" -H \"Authorization: ").append(authorization).append("\"")
                .append(" -H \"Content-Type: application/json; charset=utf-8\"")
                .append(" -H \"Host: ").append(host).append("\"")
                .append(" -H \"X-TC-Action: ").append(action).append("\"")
                .append(" -H \"X-TC-Timestamp: ").append(timestamp).append("\"")
                .append(" -H \"X-TC-Version: ").append(version).append("\"")
                .append(" -H \"X-TC-Region: ").append(region).append("\"")
                .append(" -d '").append(payload).append("'");
        System.out.println(sb.toString());
    }
}
