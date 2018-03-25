package com.chengchenrui.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;

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



    public static void main(String[] args) {
        jdkSHA_1();
        bcSHA_1();
        bcSHA_224();
    }
}