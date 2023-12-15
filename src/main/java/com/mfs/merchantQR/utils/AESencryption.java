package com.mfs.merchantQR.utils;

/*
Author Name: romail.ahmed

Project Name: configurations

Package Name: com.mfs.configurations.controller.blacklist

Class Name: AESencryption

Date and Time:3/13/2023 2:41 PM

Version:1.0
*/
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AESencryption {

	public String encryptwith256(String strToEncrypt) throws Exception {
		try {
			SecretKeySpec secretKey = generateSecretKey();
			Cipher cipher = Cipher.getInstance(Constants.paddingCBC);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(Constants.IV_PARAMETER));
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			throw new Exception(Constants.errorEncryption + e.getMessage());
		}
	}

	public String decrypt(String strToDecrypt) throws Exception {
		try {
			SecretKeySpec secretKey = generateSecretKey();
			Cipher cipher = Cipher.getInstance(Constants.paddingCBC);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(Constants.IV_PARAMETER));
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)), StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new Exception(Constants.errorDecrytion + e.getMessage());
		}
	}

	private SecretKeySpec generateSecretKey() {
		String SECRET_KEY = Constants.aes_secret_key;
		byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
		return new SecretKeySpec(keyBytes, "AES");
	}
}