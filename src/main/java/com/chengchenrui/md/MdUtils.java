package com.chengchenrui.md;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 消息摘要算法
 * @author chengchenrui
 * @version Id: DES3Utils.java, v 0.1 2018/4/30 20:31 chengchenrui Exp $$
 */
public class MdUtils {

    private static String src = "chengchenrui security md";

    public static void jdkMD5() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md.digest(src.getBytes());
            System.out.println("JDK MD5：" + Hex.encodeHexString(md5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void jdkMD2() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] md2Bytes = md.digest(src.getBytes());
            System.out.println("JDK MD2：" + Hex.encodeHexString(md2Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void bcMD4() {
        Digest digest = new MD4Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] md4Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md4Bytes, 0);
        System.out.println("BC  MD4：" + org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
    }

    public static void bcMD4AddProvider() {
        try {
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest md = MessageDigest.getInstance("MD4");
            byte[] md4Bytes = md.digest(src.getBytes());
            System.out.println("BC  MD4：" + Hex.encodeHexString(md4Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public static void bcMD5() {
        Digest digest = new MD5Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] md5Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md5Bytes, 0);
        System.out.println("BC  MD5：" + org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes));
    }

    public static void ccMD5() {
        System.out.println("CC  MD5：" + DigestUtils.md5Hex(src.getBytes()));
    }

    public static void ccMD2() {
        System.out.println("CC  MD2：" + DigestUtils.md2Hex(src.getBytes()));
    }

    public static void main(String[] args) {
        jdkMD5();
        bcMD5();
        ccMD5();
        jdkMD2();
        ccMD2();
        bcMD4();
        bcMD4AddProvider();
    }
}
