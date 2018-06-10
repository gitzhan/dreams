package com.dreams.cloud.usercenter.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptHandler {
	
	private static final String ENCODERULES = "superzhan.paint.password";
	
	private static final String TOKEN_ENCODERULES = "superzhan.paint.token";
	
	/**
	 * 加密
	 * @param content
	 * @return
	 */
	public static String aesEncode(String content) {
		return aesEncode(ENCODERULES, content);
	}
	
	/**
	 * 解密
	 * @param content
	 * @return
	 */
	public static String aesDecode(String content) {
		return aesDecode(ENCODERULES,content);
	}
	
	/**
	 * token加密
	 * @param content
	 * @return
	 */
	public static String tokenAESEncode(String token) {
		return aesEncode(TOKEN_ENCODERULES, token);
	}
	
	/**
	 * token解密
	 * @param content
	 * @return
	 */
	public static String tokenAESDecode(String token) {
		return aesDecode(TOKEN_ENCODERULES,token);
	}
	
	public static void main(String[] args) {
		System.out.println(aesEncode("testuser1"));
		System.out.println(aesDecode("0a/G7WfHt5qa/xogyw5w7w=="));
		System.out.println(tokenAESEncode("c2b8ab4584b84da1b78fc8546944aeb61507457416612"));
		System.out.println(tokenAESDecode("LFA2VyXkB5kboRxml3PM+dpBf4RFCQ8c4/3q9FgwL8rvXKfJMB8Fy+3yBsqIGjqp"));
		for (int i = 0; i < 20; i++) {
			System.out.println(aesEncode("aaa"));
		}
	}
	
	/**
	 * 解决LINUX下加密解密不一致的问题
	 * @param strKey
	 * @return
	 */
	public static SecretKey getKey(String strKey) {
        try {
            if (strKey == null) {
                strKey = "";
            }
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            generator.init(128, secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(" 初始化密钥出现异常 ");
        }
    }
	
	/*
	 * 加密 1.构造密钥生成器 2.根据ecnodeRules规则初始化密钥生成器 3.产生密钥 4.创建和初始化密码器 5.内容加密 6.返回字符串
	 */
	public static String aesEncode(String encodeRules, String content) {
		try {
			// 1.构造密钥生成器，指定为AES算法,不区分大小写
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			// 2.根据ecnodeRules规则初始化密钥生成器
			// 生成一个128位的随机源,根据传入的字节数组
			keygen.init(128, new SecureRandom(encodeRules.getBytes()));
			// 3.产生原始对称密钥
//			SecretKey original_key = keygen.generateKey();
			SecretKey originalKey = getKey(encodeRules);
			// 4.获得原始对称密钥的字节数组
			byte[] raw = originalKey.getEncoded();
			// 5.根据字节数组生成AES密钥
			SecretKey key = new SecretKeySpec(raw, "AES");
			// 6.根据指定算法AES自成密码器
			Cipher cipher = Cipher.getInstance("AES");
			// 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
			byte[] byteEncode = content.getBytes("utf-8");
			// 9.根据密码器的初始化方式--加密：将数据加密
			byte[] byteAES = cipher.doFinal(byteEncode);
			// 10.将加密后的数据转换为字符串
			// 这里用Base64Encoder中会找不到包
			// 解决办法：
			// 在项目的Build path中先移除JRE System Library，再添加库JRE System
			// Library，重新编译后就一切正常了。
			// 11.将字符串返回
			return new String(new BASE64Encoder().encode(byteAES));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 如果有错就返加nulll
		return null;
	}

	/*
	 * 解密 解密过程： 1.同加密1-4步 2.将加密后的字符串反纺成byte[]数组 3.将加密内容解密
	 */
	public static String aesDecode(String encodeRules, String content) {
		try {
			// 1.构造密钥生成器，指定为AES算法,不区分大小写
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			// 2.根据ecnodeRules规则初始化密钥生成器
			// 生成一个128位的随机源,根据传入的字节数组
			keygen.init(128, new SecureRandom(encodeRules.getBytes()));
			// 3.产生原始对称密钥
//			SecretKey original_key = keygen.generateKey();
			SecretKey originalKey = getKey(encodeRules);
			// 4.获得原始对称密钥的字节数组
			byte[] raw = originalKey.getEncoded();
			// 5.根据字节数组生成AES密钥
			SecretKey key = new SecretKeySpec(raw, "AES");
			// 6.根据指定算法AES自成密码器
			Cipher cipher = Cipher.getInstance("AES");
			// 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
			cipher.init(Cipher.DECRYPT_MODE, key);
			// 8.将加密并编码后的内容解码成字节数组
			byte[] byteContent = new BASE64Decoder().decodeBuffer(content);
			/*
			 * 解密
			 */
			byte[] byteDecode = cipher.doFinal(byteContent);
			return new String(byteDecode, "utf-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		// 如果有错就返加nulll
		return null;
	}

}
