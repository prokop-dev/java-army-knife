package dev.prokop.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    private HashUtils() {
    }

    public static MessageDigest sha256() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new AssertionError("It strange, but JVM does not have SHA-256 implementation...", noSuchAlgorithmException);
        }
    }

    public static MessageDigest sha512() {
        try {
            return MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new AssertionError("It strange, but JVM does not have SHA-512 implementation...", noSuchAlgorithmException);
        }
    }

    public static Mac hmacSha512(byte[] key) {
        if (key == null) throw new IllegalArgumentException("HMAC key cannot be null reference");
        try {
            final Mac mac = Mac.getInstance("HmacSHA512");
            final SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA512");
            mac.init(secretKeySpec);
            return mac;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("It strange, but JVM does not have HMAC with SHA-512 implementation...", e);
        } catch (InvalidKeyException invalidKeyException) {
            throw new IllegalArgumentException("Unable to initialize HMAC with provided key", invalidKeyException);
        }
    }

    /**
     * SHA-256
     *
     * @param bytes input
     * @return sha256(input)
     */
    public static byte[] sha256(byte[] bytes) {
            return sha256().digest(bytes);
    }

    /**
     * SHA-512
     *
     * @param bytes input
     * @return sha512(input)
     */
    public static byte[] sha512(byte[] bytes) {
            return sha512().digest(bytes);
    }

    /**
     * HMAC SHA-512
     *
     * @param key HMAC secret key
     * @param bytes input
     * @return hmacSha512(secret_key, input)
     */
    public static byte[] hmacSha512(byte[] key, byte[] bytes) {
        return hmacSha512(key).doFinal(bytes);
    }

}
