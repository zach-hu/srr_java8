package com.tsa.puridiom.supplierportal;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
/**
 * @version     2.0
 * @author Kelli Knisely, Zach Bricker
 */
public class BlackBox
{
    /**
         *  getEncrypt Method
         *  Encrypts/Decrypts a string.
         *  @param  s   String "ENCRYPT" or "DECRYPT".
         *  @param  s1  String value of the string to be encrypted.
         *  @return String
         */
    public static String getEncrypt(String s, String s1)
    {
        String ivkey = "y0Difdw5it5XIl8V";
        byte[] ivraw = ivkey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(ivraw, "AES");
        byte[] iv = { 
'y','3','v','w','9','v','j','f','p','f','3','b','2','w','s','7' };
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        if(s.equalsIgnoreCase("DECRYPT"))
        {
            try {
                Cipher cipher = 
Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivspec);
                byte[] encrypted = 
cipher.doFinal(Base64.decodeBase64(s1));
                s1 = new String(encrypted);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(s.equalsIgnoreCase("ENCRYPT"))
        {
            try {
                Cipher cipher = 
Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec);
                byte[] encrypted = cipher.doFinal(s1.getBytes());
                s1 = new String(Base64.encodeBase64(encrypted));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return s1;
    }

        /**
         *  getEncrypt Method
         *  Encrypts a string.
         *  @param s String value of string to be encrypted.
         *  @return String
         */
    public static String getEncrypt(String s)
    {
        return getEncrypt("ENCRYPT", s);
    }

        /**
         *  getDecrypt Method
         *  Decrypts a string
         *  @param s String value of string to be decrypted.
         *  @return String
         */
    public static String getDecrypt(String s)
    {
        return getEncrypt("DECRYPT", s);
    }

        /**
     *  getStringEncrypt Method
     *  Encrypts a string using the String value of the encrypted 
character.
     *  @param  s  String value of the string to be encrypted.
     *  @return String
     */
    public static String getStringEncrypt(String s)
    {
        int    i = s.length();
        char    ac[] = new char[i];
        int    ai[] = new int[i];
        String    temp = "";
        String    sreturn = "";

        s.getChars(0, i, ac, 0);

        for (int k = 0; k < ac.length; k++)
        {
            ai[k] = ac[i - 1] + i;

            i--;
        }

        for (int l = 0; l < ai.length; l++)
        {
            temp = String.valueOf(ai[l]);
            while (temp.length() < 3)
            {
                temp = "0" + temp;
            }

            sreturn = sreturn + temp;
        }

        return sreturn;
    }
}

