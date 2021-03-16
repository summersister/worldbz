package com.richman.common.util.jwt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES的加密和解密
 */
public class AesUtil {

    //密钥
    public static final String PASSWORD = "7DJgTLRZ17WLTm18";
    //加密算法
    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";
    //偏移量
    private static final String IV_STRING = "JZqrMKbMk5H6ewER";

    /**
     * CBC算法解密
     *
     * @param encrypt
     * @return
     */
    public static String aesDecryptCbc(String encrypt) {

        try {
            return aesDecryptCbc(encrypt, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * CBC 算法加密
     *
     * @param content
     * @return
     */
    public static String aesEncrypt(String content) {
        try {
            return aesEncrypt(content, PASSWORD).replaceAll("\r\n", "").replaceAll("\n", "");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * base 64 encode
     *
     * @param bytes  待编码的byte[]
     * @return  编码后的base 64 code
     */
    private static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return  解码后的byte[]
     * @throws Exception
     */
    private static byte[] base64Decode(String base64Code) throws Exception {
        return new BASE64Decoder().decodeBuffer(base64Code);
    }

    /**
     * @param content  待加密的内容
     * @param encryptKey  加密密钥
     * @return  加密后的byte[]
     * @throws Exception
     */
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        byte[] initParam = IV_STRING.getBytes();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"), ivParameterSpec);
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * @param content  待加密的内容
     * @param encryptKey  加密密钥
     * @return   加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * @param encryptBytes  encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return   解密后的String
     * @throws Exception
     */
    private static String aesDecryptByBytesCbc(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        byte[] initParam = IV_STRING.getBytes();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"), ivParameterSpec);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr  待解密的base 64 code
     * @param decryptKey  解密密钥
     * @return   解密后的string
     * @throws Exception
     */
    public static String aesDecryptCbc(String encryptStr, String decryptKey) throws Exception {

        return aesDecryptByBytesCbc(base64Decode(encryptStr), decryptKey);
    }

}