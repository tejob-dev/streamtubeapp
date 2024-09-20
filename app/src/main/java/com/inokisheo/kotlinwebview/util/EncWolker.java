package com.inokisheo.kotlinwebview.util;

public class EncWolker {

    public static String encro(String text){
        String cipherText = Base64.getEncoder().encodeToString(text.getBytes());
        System.out.println("Result Encrypt "+cipherText);
        return cipherText;
    }

    public static String dencro(String textEnc){
        byte[] decodedBytes = Base64.getDecoder().decode(textEnc);
        String plainText = new String(decodedBytes);
        //System.out.println("Text Decrypt "+plainText);
        return plainText;
    }
}
