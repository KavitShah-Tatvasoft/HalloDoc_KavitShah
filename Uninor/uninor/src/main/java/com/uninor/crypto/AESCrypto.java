package com.uninor.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESCrypto {

    public static String encrypt(String data, SecretKey secretKey, IvParameterSpec iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return CryptoUtil.toBase64(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String encryptedData, SecretKey secretKey, IvParameterSpec iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] decryptedData = cipher.doFinal(CryptoUtil.fromBase64(encryptedData));
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        // Generate a secret key and an IV
        SecretKey secretKey = CryptoUtil.generateAESKey(256);
        IvParameterSpec iv = CryptoUtil.generateIV();

        // Encrypt some data
        String data = "Jemis is a baaaaddd boiiiiii";
        String encryptedData = AESCrypto.encrypt(data, secretKey, iv);
        System.out.println("Encrypted data: " + encryptedData);

        // Decrypt the data
        String decryptedData = AESCrypto.decrypt(encryptedData, secretKey, iv);
        System.out.println("Decrypted data: " + decryptedData);


        String stringToEncode = "TutorialsPoint?java";

// Encode using basic encoder
        String base64encodedString = Base64.getEncoder().encodeToString(stringToEncode.getBytes("utf-8"));

        System.out.println("Encoded String: " + base64encodedString);



        byte[] base64DecodedBytes = Base64.getDecoder().decode(base64encodedString);
        String decodedString = new String(base64DecodedBytes);

        System.out.println("Decoded String: " + decodedString);
    }
}