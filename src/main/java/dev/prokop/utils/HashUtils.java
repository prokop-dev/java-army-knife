package dev.prokop.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    private HashUtils() {
    }

    public static byte[] sha256(byte[] bytes) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(bytes);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new AssertionError("It strange but JVM does not have SHA-256 implementation...", noSuchAlgorithmException);
        }
    }

}
