package com.gg.dailymoney.init;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Criptography {

    public Criptography() {
    }

    public static String encript(String str) {
        String passPhrase = activationKey;
        return encript(str, passPhrase);
    }

    public static String encript(String str, String passPhrase) {
        Cipher ecipher = null;
        Cipher dcipher = null;
        try {
            java.security.spec.KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), SALT_BYTES, ITERATION_COUNT);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());
            java.security.spec.AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT_BYTES, ITERATION_COUNT);
            ecipher.init(1, key, paramSpec);
            dcipher.init(2, key, paramSpec);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        try {
            byte utf8[] = str.getBytes("UTF8");
            byte enc[] = ecipher.doFinal(utf8);
            String encoded = (new BASE64Encoder()).encode(enc);
            int j = 0;
            char c[] = encoded.toCharArray();
            char r[] = new char[c.length - 2];
            for (int i = 0; i < c.length; i++) {
                if (c[i] != '\r' && c[i] != '\n') {
                    r[j++] = c[i];
                }
            }

            return new String(r);
        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public static String decript(String str) {
        String passPhrase = activationKey;
        return decript(str, passPhrase);
    }

    public static String decript(String str, String passPhrase) {
        Cipher ecipher = null;
        Cipher dcipher = null;
        try {
            java.security.spec.KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), SALT_BYTES, ITERATION_COUNT);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());
            java.security.spec.AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT_BYTES, ITERATION_COUNT);
            ecipher.init(1, key, paramSpec);
            dcipher.init(2, key, paramSpec);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        try {
            byte dec[] = (new BASE64Decoder()).decodeBuffer(str);
            byte utf8[] = dcipher.doFinal(dec);
            return new String(utf8, "UTF8");
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static final String activationKey = "Imothek@mindBreaKeR$1983%";
    public static final String licenseKey = "softFreaK@Developer2008";
    private static byte SALT_BYTES[] = {
        -87, -101, -56, 50, 86, 53, -29, 3
    };
    private static int ITERATION_COUNT = 19;
}