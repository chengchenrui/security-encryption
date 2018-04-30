package com.chengchenrui.aes;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 对称加密算法
 * @author chengchenrui
 * @version Id: AESUtils.java, v 0.1 2018/4/30 20:42 chengchenrui Exp $$
 */
public class AESUtils {

    private static String src = "chengchenrui security aes";

    public static void jdkAES() {
        //生成Key
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //keyGenerator.init(128);
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            //key转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            //加密(加密方式/工作模式/填充方式)
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk aes encrypt：" + Base64.encodeBase64String(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);

            System.out.println("jdk aes decrypt：" + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bcAES() {
        // TODO 与des相近
    }

    public static void main(String[] args) {
        jdkAES();
    }
}