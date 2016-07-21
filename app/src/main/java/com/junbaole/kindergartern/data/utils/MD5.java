package com.junbaole.kindergartern.data.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
    /**
     * this method is used for sign request params
     *
     * @param inputStr input str
     * @return md5 str
     * @throws Exception exception
     */
    public static String getMD5(String inputStr) throws Exception {
        String md5Str = inputStr;
        if (inputStr != null) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inputStr.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            md5Str = hash.toString(16);
            if ((md5Str.length() % 2) != 0) {
                md5Str = "0" + md5Str;
            }
        }
        return md5Str;
    }

    public static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}