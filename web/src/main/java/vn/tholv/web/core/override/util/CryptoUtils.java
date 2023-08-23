package vn.tholv.web.core.override.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtils {
    private static String ALGORITHM = "AES";

    private static String SECRET = "daEIDi25ctoGiwE3";

    public static String encode(CharSequence rawPassword) {
        try {
            String password = rawPassword.toString();
            SecretKeySpec keySpec = new SecretKeySpec(SECRET.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedBytes = cipher.doFinal(password.toString().getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(SECRET.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] encryptedBytes = Base64.getDecoder().decode(encodedPassword);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes).equals(rawPassword.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String decrypt(String encryptedInput) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedInput);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

}
