package com.example.loginpage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class EncodeAES {

    private static final String AES_MODE = "AES/CBC/PKCS5Padding";
    private static final String CHARSET_NAME = "UTF-8";
    private static final String PASSWORD = "pass123"; // Replace with a strong and secure password

    public static String enc(String data) {
        try {
            byte[] salt = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(PASSWORD.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance(AES_MODE);
            IvParameterSpec ivParams = new IvParameterSpec(generateRandomIV());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);

            byte[] encryptedData = cipher.doFinal(data.getBytes(CHARSET_NAME));
            byte[] combined = new byte[salt.length + ivParams.getIV().length + encryptedData.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(ivParams.getIV(), 0, combined, salt.length, ivParams.getIV().length);
            System.arraycopy(encryptedData, 0, combined, salt.length + ivParams.getIV().length, encryptedData.length);

            return Base64.encodeToString(combined, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] generateRandomIV() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return iv;
    }
}
