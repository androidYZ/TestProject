package com.oneonezz.utils;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by ${Justin} on 2020/4/21.
 */
public class SecurityUtil {
    private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMVeLWwr5E848Lgz\n" +
            "PauxqTgnZlK+uONQxh/Qc/of417pkvpMbUM8DdfkMLKQvZraS2KkavJZLx4SF4pZ\n" +
            "zyMSyVKUYa7NXSmNIwvodkZ5+boj4rDvU9sORBpGTT2iGtlUVyff1IGsq1xrZwO+\n" +
            "0Js25Tp3DKEpLo52mwk4t5BwPWS1AgMBAAECgYEAnPxr5L6Euv7veZRIWWjW0HOG\n" +
            "UAqjxStaa7PCjBERKU1gOvbPccsyt6Ypld7K8YDwtWVSK7IEaEa7sq/8skkfF8oq\n" +
            "IxNlWvz9cU5+2bpo4l6p9jf98itCRku12vHURfjpKPUJDHhEw08HHJzygzwEjBa5\n" +
            "92z/VYMOS7vzl8/COAECQQDsGXD8TH8FT2poJkentikriXaUaEhvEabkD69T9zdw\n" +
            "vEEpJycXhIuAlNdhrGiwxsGaQV5MgjiISfsuYwCa/SwxAkEA1gD8nGC+KbrjSgQM\n" +
            "/4LBzqSO5KSlv8jCNIc6MP3Mkc/aJnsO1D3waFV+sPxv5hm3vxAGKtKrGGogYHPl\n" +
            "Jl7TxQJAL7y5JppE+IrlJefPhy6CxoXFzx4Cv0pfZy9wsNaEhCvcNPjwo4WuOzJd\n" +
            "FiepB004qfrbmTafPZdPzUJBRhU4kQJAMEq2tn8qSnZu9fPmDv6tx3jg+f2hnZ6r\n" +
            "HHlri0SqljjmTIq2dPSpPJIWbc/5XF5vVYFDMwSpMXA5cCqXCkqZ1QJACOgqFna/\n" +
            "lq98krSxy0QrQGf2TeaJBFzvlQcN4/J6YRl73/fsDlyKFGSHgJp6cqsGvAA8IUov\n" +
            "9vEZkAPG0lmtrA==";

    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFXi1sK+RPOPC4Mz2rsak4J2ZS\n" +
            "vrjjUMYf0HP6H+Ne6ZL6TG1DPA3X5DCykL2a2ktipGryWS8eEheKWc8jEslSlGGu\n" +
            "zV0pjSML6HZGefm6I+Kw71PbDkQaRk09ohrZVFcn39SBrKtca2cDvtCbNuU6dwyh\n" +
            "KS6OdpsJOLeQcD1ktQIDAQAB";

    /**
     * RSA算法
     */
    public static final String RSA = "RSA";
    /**
     * 加密方式，标准jdk的
     */
    public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    /** 这个是Android的 */
//    public static final String TRANSFORMATION = "RSA/None/PKCS1Padding";


    /**
     * ****************************************************************************************
     * *     Base64  <start>
     * ****************************************************************************************
     */

    private static char[] base64EncodeChars = new char[]
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                    'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
                    '6', '7', '8', '9', '+', '/'};
    private static byte[] base64DecodeChars = new byte[]
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53,
                    54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
                    12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29,
                    30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1,
                    -1, -1, -1};

    /**
     * 解密
     *
     * @param str
     * @return
     */
    public static byte[] decode2base64(String str) {
        try {
            return decodePrivate(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new byte[]
                {};
    }

    private static byte[] decodePrivate(String str) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        byte[] data = null;
        data = str.getBytes("US-ASCII");
        int len = data.length;
        int i = 0;
        int b1, b2, b3, b4;
        while (i < len) {

            do {
                b1 = base64DecodeChars[data[i++]];
            } while (i < len && b1 == -1);
            if (b1 == -1)
                break;

            do {
                b2 = base64DecodeChars[data[i++]];
            } while (i < len && b2 == -1);
            if (b2 == -1)
                break;
            sb.append((char) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

            do {
                b3 = data[i++];
                if (b3 == 61)
                    return sb.toString().getBytes("iso8859-1");
                b3 = base64DecodeChars[b3];
            } while (i < len && b3 == -1);
            if (b3 == -1)
                break;
            sb.append((char) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

            do {
                b4 = data[i++];
                if (b4 == 61)
                    return sb.toString().getBytes("iso8859-1");
                b4 = base64DecodeChars[b4];
            } while (i < len && b4 == -1);
            if (b4 == -1)
                break;
            sb.append((char) (((b3 & 0x03) << 6) | b4));
        }
        return sb.toString().getBytes("iso8859-1");
    }

    public static String encode2base64(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }
    /**
     * ****************************************************************************************
     * *     Base64  <end>
     * ****************************************************************************************
     */


    /**
     * ****************************************************************************************
     * *     AES加密  <start>
     * ****************************************************************************************
     */
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法
    // SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法
    private static final String SHA1PRNG = "SHA1PRNG";

    public static byte[] generateRandomKey() {
        try {
            SecureRandom localSecureRandom = SecureRandom.getInstance(SHA1PRNG);
            byte[] bytes_key = new byte[20];
            localSecureRandom.nextBytes(bytes_key);
            return bytes_key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final byte[] key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(key));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static String aesEncrypt(String content, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return encode2base64(result);//通过Base64转码返回

        } catch (Exception ex) {
            Logger.getLogger(SecurityUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static String aesDecrypt(String content, byte[] key) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));

            //执行操作
            byte[] result = cipher.doFinal(decode2base64(content));


            return new String(result, "utf-8");
        } catch (Exception ex) {
            Logger.getLogger(SecurityUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }


    /**
     * 使用公钥加密
     */
    public static byte[] rsaEncrypt(byte[] data, byte[] publicKey) throws Exception {
        // 得到公钥对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        // 加密数据
        Cipher cp = Cipher.getInstance(TRANSFORMATION);
        cp.init(Cipher.ENCRYPT_MODE, pubKey);
        return cp.doFinal(data);
    }

    /**
     * 使用私钥解密
     */
    public static byte[] rsaDecrypt(byte[] encrypted, byte[] privateKey) throws Exception {
        // 得到私钥对象
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance(RSA);
        PrivateKey keyPrivate = kf.generatePrivate(keySpec);
        // 解密数据
        Cipher cp = Cipher.getInstance(TRANSFORMATION);
        cp.init(Cipher.DECRYPT_MODE, keyPrivate);
        byte[] arr = cp.doFinal(encrypted);
        return arr;
    }

    /**
     * ****************************************************************************************
     * *     测试  <start>
     * ****************************************************************************************
     */

    public static String rsaEncryptTest(byte[] content) throws Exception {
        return encode2base64(rsaEncrypt(content, decode2base64(publicKey)));
    }

    public static byte[] rsaDecryptTest(String content) throws Exception {
        return rsaDecrypt(decode2base64(content), decode2base64(privateKey));
    }

    public static void testRsa() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 57; i++) {
            sb.append("杨");
        }
        String test = sb.toString();

        byte[] encrypt = rsaEncrypt(test.getBytes("unicode"), decode2base64(publicKey));

        String newString = encode2base64(encrypt);

        System.out.println("T.T->testRsa: " + newString);

        byte[] decrypt = rsaDecrypt(decode2base64(newString), decode2base64(privateKey));
        System.out.println("T.T->testRsa: " + new String(decrypt, "unicode"));
    }

    public static void main(String[] args) throws Exception {
        String content = "hello,您好呀";

        byte[] aesKey = generateRandomKey();
        String aesString = aesEncrypt(content, aesKey);
        String keySend = rsaEncryptTest(aesKey);

        byte[] tempDecrypt = rsaDecryptTest(keySend);
        String decContent = aesDecrypt(aesString, tempDecrypt);

        System.out.println("T.T->main:--:" + decContent);
    }
}
