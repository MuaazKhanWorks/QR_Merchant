package com.mfs.merchantQR.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES256Encryption {

    public String encrypt(String strToEncrypt) {
        try {
            SecretKeySpec secretKey = generateSecretKey();
            Cipher cipher = Cipher.getInstance(Constants.paddingCBC);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(Constants.IV_PARAMETER));
            byte[] encryptedBytes = cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.err.println(Constants.errorEncryption + e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt) {
        try {
            SecretKeySpec secretKey = generateSecretKey();
            Cipher cipher = Cipher.getInstance(Constants.paddingCBC);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(Constants.IV_PARAMETER));
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println(Constants.errorDecrytion + e.toString());
        }
        return null;
    }

    private SecretKeySpec generateSecretKey() throws NoSuchAlgorithmException {
        String SECRET_KEY = Constants.aes_secret_key;
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, "AES");
    }
}
