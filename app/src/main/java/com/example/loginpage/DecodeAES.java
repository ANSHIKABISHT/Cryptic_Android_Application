package com.example.loginpage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;
import java.security.spec.KeySpec;

public class DecodeAES {

    private static final String AES_MODE = "AES/CBC/PKCS5Padding";
    private static final String CHARSET_NAME = "UTF-8";
    private static final String PASSWORD = "pass123"; // Replace with the same strong and secure password used for encryption

    public static String dec(String encodedData) {
        try {
            byte[] combined = Base64.decode(encodedData, Base64.DEFAULT);
            byte[] salt = new byte[16];
            byte[] iv = new byte[16];
            byte[] encryptedData = new byte[combined.length - salt.length - iv.length];
            System.arraycopy(combined, 0, salt, 0, salt.length);
            System.arraycopy(combined, salt.length, iv, 0, iv.length);
            System.arraycopy(combined, salt.length + iv.length, encryptedData, 0, encryptedData.length);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(PASSWORD.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance(AES_MODE);
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);

            byte[] decryptedData = cipher.doFinal(encryptedData);
            return new String(decryptedData, CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
