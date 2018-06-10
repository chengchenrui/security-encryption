package com.chengchenrui.messagedigest.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 消息摘要算法
 * @author chengchenrui
 * @version Id: DES3Utils.java, v 0.1 2018/4/30 20:31 chengchenrui Exp $$
 */
public class SHAUtils {

    private static String src = "chengchenrui security sha";

    public static void jdkSHA_1() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(src.getBytes());
            System.out.println("JDK sha-1  ：" + Hex.encodeHexString(md.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void bcSHA_1() {
        Digest digest = new SHA1Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] sha1Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(sha1Bytes, 0);
        System.out
            .println("BC  sha-1  ：" + org.bouncycastle.util.encoders.Hex.toHexString(sha1Bytes));
    }

    public static void bcSHA_224() {
        Digest digest = new SHA224Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] sha224Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(sha224Bytes, 0);
        System.out
            .println("BC  sha-224：" + org.bouncycastle.util.encoders.Hex.toHexString(sha224Bytes));
    }

    public static void bcSHA_224_2() {
        Security.addProvider(new BouncyCastleProvider());
        // ...
    }

    public static void ccSHA1() {
        System.out.println("cc  sha1 -1：" + DigestUtils.sha1Hex(src.getBytes()));
        System.out.println("cc  sha1 -2：" + DigestUtils.sha1Hex(src));
    }

    public static void main(String[] args) {
        jdkSHA_1();
        bcSHA_1();
        bcSHA_224();
        ccSHA1();
    }
}
