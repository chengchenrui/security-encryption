package com.chengchenrui.des;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

/**
 * @author chengchenrui
 * @version Id: DesUtils.java, v 0.1 2018/3/28 23:03 chengchenrui Exp $$
 */
public class DesUtils {

    private static String src = "chengchenrui security des";

    public static void jdkDES() {

        try {
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            // key转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk des encrypt：" + Hex.encodeHexString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk des decrypt：" + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建议使用这种
     */
    public static void bcDES(){

        try {
            Security.addProvider(new BouncyCastleProvider());
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            // key转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("bc  des encrypt：" + Hex.encodeHexString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("bc  des decrypt：" + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        jdkDES();
        bcDES();
    }
}