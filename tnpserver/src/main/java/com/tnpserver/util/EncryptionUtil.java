package com.tnpserver.util;

import com.tnpserver.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Utility class for performing AES encryption and decryption.
 */
public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;
    private static final String secretKey = "JqU#VY47!9r*HLhGc^zso$P1iRld^6#K";

    private static Logger LOG = LoggerFactory.getLogger(EncryptionUtil.class);
    private static SecretKey key;

    static {
        try {
            key = generateKey(secretKey);
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Error Occurred while generating Key {}", e);
        }
    }

    private EncryptionUtil(){}

    /**
     * Encrypts the given plain text using AES encryption algorithm.
     *
     * @param plainText The text to be encrypted.
     * @return The Base64-encoded encrypted text.
     * @throws Exception If an error occurs during encryption.
     */
    public static String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts the given Base64-encoded cipher text using AES decryption algorithm.
     *
     * @param cipherText The Base64-encoded encrypted text.
     * @return The decrypted plain text.
     * @throws Exception If an error occurs during decryption.
     */
    public static String decrypt(String cipherText) {
        try {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOG.error("Unknown error occurred while decrypting data. Error - {} ", e);
            throw new BusinessException("Unknown error occurred while decrypting data.");
        }
    }

    private static SecretKey generateKey(String secretKey) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(KEY_SIZE);
        return new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
    }

}
