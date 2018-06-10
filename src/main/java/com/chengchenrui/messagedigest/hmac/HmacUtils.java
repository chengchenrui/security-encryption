package com.chengchenrui.messagedigest.hmac;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

/**
 * 消息摘要算法
 * @author chengchenrui
 * @version Id: HmacUtils.java, v 0.1 2018/3/28 22:27 chengchenrui Exp $$
 */
public class HmacUtils {

    private static String src = "chengchenrui security Hmac";

    public static void jdkHmacMD5() {
        try {
            // 初始化KeyGenerator
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
            // 产生秘钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 获得秘钥
            byte[] key = secretKey.getEncoded();
            //byte[] key = Hex
            //    .decodeHex(new char[] { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' });
            //还原秘钥
            SecretKey restoreSecretKey = new SecretKeySpec(key, "HmacMD5");
            //实例化MAC
            Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
            //初始化MAC
            mac.init(restoreSecretKey);

            byte[] hmacMD5Bytes = mac.doFinal(src.getBytes());
            System.out.println("jdk hmacMD5：" + Hex.encodeHexString(hmacMD5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bcHmacMD5() {
        HMac hMac = new HMac(new MD5Digest());
        hMac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("aaaaaaaaaa")));
        hMac.update(src.getBytes(), 0, src.getBytes().length);
        byte[] hmacMD5Bytes = new byte[hMac.getMacSize()];
        hMac.doFinal(hmacMD5Bytes, 0);
        System.out
            .println("bc  hmacMD5：" + org.bouncycastle.util.encoders.Hex.toHexString(hmacMD5Bytes));
    }

    public static void main(String[] args) {
        jdkHmacMD5();
        bcHmacMD5();
    }
}