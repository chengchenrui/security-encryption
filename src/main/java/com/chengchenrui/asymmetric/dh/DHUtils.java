package com.chengchenrui.asymmetric.dh;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

/**
 * @author chengchenrui
 * @version Id: DHUtils.java, v 0.1 2018.6.10 16:56 chengchenrui Exp $$
 */
public class DHUtils {

    public static void jdkHD() {
        try {
            // 1.初始化发送方秘钥
            KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            senderKeyPairGenerator.initialize(512);
            KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
            //发送方公钥，发送给接收方（网络，文件）
            byte[] senderPublicEnc = senderKeyPair.getPublic().getEncoded();

            // 2.初始化接收方秘钥
            KeyFactory receiverKeyFactory = KeyFactory.getInstance("DH");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicEnc);
            PublicKey receiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);
            DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey).getParams();
            KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            receiverKeyPairGenerator.initialize(dhParameterSpec);
            KeyPair receiverKeyPair = receiverKeyPairGenerator.generateKeyPair();
            PrivateKey receiverPrivateKey = receiverKeyPair.getPrivate();
            byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic().getEncoded();

            //3.秘钥构建
            KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
            receiverKeyAgreement.init(receiverPrivateKey);
            receiverKeyAgreement.doPhase(receiverPrivateKey, true);
            SecretKey receiverSecretKey = receiverKeyAgreement.generateSecret("DES");

            KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
            x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
            PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
            KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
            senderKeyAgreement.init(senderKeyPair.getPrivate());
            senderKeyAgreement.doPhase(senderPublicKey, true);

            SecretKey senderSecretKey = senderKeyAgreement.generateSecret("DES");

            if (Objects.equals(receiverSecretKey, senderSecretKey)) {
                System.out.println("双方秘钥相同");
            }

        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | InvalidKeySpecException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
