package com.zb.utill;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password="2";
        String pwd1 = encodePassword(password);
        String pwd2 = encodePassword(password);
        boolean f = bCryptPasswordEncoder.matches("2","{noop}1");

      System.out.println(f);
        System.out.println(pwd2);
        System.out.println(pwd2.length());
        System.out.println(pwd1);
        System.out.println(pwd1.length());
    }
}
