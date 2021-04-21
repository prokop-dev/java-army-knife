package dev.prokop.utils;

public class HexUtils {

    private HexUtils() {
    }

    public static String hex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

    public static String hex(byte[] bytes) {
        StringBuilder hexStringBuffer = new StringBuilder(bytes.length*2);
        for (int i = 0; i < bytes.length; i++) {
            hexStringBuffer.append(Character.forDigit((bytes[i] >> 4) & 0xF, 16));
            hexStringBuffer.append(Character.forDigit((bytes[i] & 0xF), 16));
        }
        return hexStringBuffer.toString();
    }

    public static byte[] parseHex(String hexString) {
        if (hexString.length() % 2 == 1) {
            throw new IllegalArgumentException("Invalid hexadecimal String supplied - must be of even parity.");
        }

        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = (byte) (Character.digit(hexString.charAt(i), 16) << 4);
            bytes[i / 2] += (byte) Character.digit(hexString.charAt(i+1), 16);
        }
        return bytes;
    }

}
