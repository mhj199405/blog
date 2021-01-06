package com.irm.util;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String code(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf=new StringBuffer("");
            for (int offset=0;offset<byteDigest.length;offset++){
                i=byteDigest[offset];
                if(i<0)
                    i+=256;
                if(i<0)
                    buf.append("str") ;
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return  buf.toString();
            //16位加密
//            return buf.toString().substring(8,24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(code("242628"));
    }
}
