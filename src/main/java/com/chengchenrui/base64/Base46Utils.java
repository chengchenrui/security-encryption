package com.chengchenrui.base64;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base46Utils {

    private static String src = "chengchenrui security basse64";

    public static void jdkBase64() {

        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(src.getBytes());
        System.out.println("encode：" + encode);

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            System.out.println("decode：" + new String(decoder.decodeBuffer(encode)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void commonsCodecBase64() {
        byte[] encodeBytes = Base64.encodeBase64(src.getBytes());
        System.out.println("encode：" + new String(encodeBytes));

        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
        System.out.println("decode：" + new String(decodeBytes));
    }

    public static void bouncyCastleBase64() {
        byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        System.out.println("encode：" + new String(encodeBytes));

        byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
        System.out.println("decode：" + new String(decodeBytes));

    }

    public static void main(String[] args) {
        System.out.println("jdk");
        jdkBase64();
        System.out.println("commons-codec");
        commonsCodecBase64();
        System.out.println("bouncycastle");
        bouncyCastleBase64();

    }
}
