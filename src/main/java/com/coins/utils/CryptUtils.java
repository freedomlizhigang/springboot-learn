package com.coins.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/*
 * 加密解密单元
 */
public class CryptUtils {
	/**
	 * 进行MD5加密
	 * 
	 * @param info 要加密的信息
	 * @return String 加密后的字符串
	 */
	public static String encryptToMD5(String info) {
		if (info == null || info.length() == 0) {
			throw new IllegalArgumentException("不能输入空字符串");
		}
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(info.getBytes());
			byte[] hash = md.digest();
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}
 
	/**
	 * 进行SHA加密
	 * 
	 * @param info 要加密的信息
	 * @return String 加密后的字符串
	 */
	public static String encryptToSHA(String info) {
		byte[] digesta = null;
		try {
			// 得到一个SHA-1的消息摘要
			MessageDigest alga = MessageDigest.getInstance("SHA-1");
			// 添加要进行计算摘要的信息
			alga.update(info.getBytes());
			// 得到该摘要
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将摘要转为字符串
		String rs = byte2hex(digesta);
		return rs;
	}
 
	/**
	 * 根据相应的解密算法、指定的密钥和需要解密的文本进行解密，返回解密后的文本内容
	 * @param Algorithm 加密算法:DES,AES
	 * @param key 这个key可以由用户自己指定 注意AES的长度为16位,DES的长度为8位
	 * @param sInfo
	 * @return
	 */
	public static String decrypt(String Algorithm, String sSrc, String sKey) throws Exception {
		// 判断Key是否正确
		if (sKey == null) {
			throw new Exception("Key为空null");
		}
		// 判断采用AES加解密方式的Key是否为16位
		if (Algorithm.equals("AES") && sKey.length() != 16) {
			throw new Exception("Key长度不是16位");
		}
		// 判断采用DES加解密方式的Key是否为8位
		if (Algorithm.equals("DES") && sKey.length() != 8) {
			throw new Exception("Key长度不是8位");
		}
		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, Algorithm);
		Cipher cipher = Cipher.getInstance(Algorithm);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] encrypted1 = hex2byte(sSrc);
		try {
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		} catch (Exception e) {
			throw e;
		}
	}
 
	/**
	 * 根据相应的加密算法、指定的密钥、源文件进行加密，返回加密后的文件
	 * @param Algorithm 加密算法:DES,AES
	 * @param key 这个key可以由用户自己指定 注意AES的长度为16位,DES的长度为8位
	 * @param info
	 * @return
	 */
	public static String encrypt(String Algorithm, String sSrc, String sKey) throws Exception {
		// 判断Key是否正确
		if (sKey == null) {
			throw new Exception("Key为空null");
		}
		// 判断采用AES加解密方式的Key是否为16位
		if (Algorithm.equals("AES") && sKey.length() != 16) {
			throw new Exception("Key长度不是16位");
		}
		// 判断采用DES加解密方式的Key是否为8位
		if (Algorithm.equals("DES") && sKey.length() != 8) {
			throw new Exception("Key长度不是8位");
		}
		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, Algorithm);
		Cipher cipher = Cipher.getInstance(Algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());
		return byte2hex(encrypted);
	}
 
	/**
	 * 采用DES指定密钥的方式进行加密
	 * @param key
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public static String encryptToDES(String key, String info) throws Exception {
		return encrypt("DES", info, key);
	}
 
	/**
	 * 采用DES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样
	 * @param key
	 * @param sInfo
	 * @return
	 */
	public static String decryptByDES(String key, String sInfo) throws Exception {
		return decrypt("DES", sInfo, key);
	}

	/**
	 * 采用AES指定密钥的方式进行加密
	 * @param key
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public static String encryptToAES(String key, String info) throws Exception {
		return encrypt("AES", info, key);
	}

	/**
	 * 采用AES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样
	 * @param key
	 * @param sInfo
	 * @return
	 */
	public static String decryptByAES(String key, String sInfo) throws Exception {
		return decrypt("AES", sInfo, key);
	}
 
	/**
	 * 十六进制字符串转化为2进制
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hex2byte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int l = strhex.length();
		if (l % 2 == 1) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
		}
		return b;
	}
 
	/**
	 * 将二进制转化为16进制字符串
	 * 
	 * @param b 二进制字节数组
	 * @return String
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		CryptUtils encryptUtils = new CryptUtils();
//		Long times = new Date().getTime();
//		String source = "c-token.11." + times;
//		System.out.println("Hello经过MD5:" + encryptUtils.encryptToMD5(source));
//		System.out.println("Hello经过SHA:" + encryptUtils.encryptToSHA(source));
//		System.out.println("========随机生成Key进行加解密==============");
//		// 生成一个DES算法的密匙
////		SecretKey key = encryptUtils.createSecretDESKey();
//		String keyPro = "firefly@";
//		System.out.println(keyPro);
//		String str1 = encryptUtils.encryptToDES(keyPro, source);
//		System.out.println("DES encrypt:" + str1);
//		// 使用这个密匙解密
//		String str2 = encryptUtils.decryptByDES(keyPro, str1);
//		System.out.println("DES decrypt:" + str2);
//
//		// 生成一个AES算法的密匙
		String keyAes = "fireflycoltd!@#$";
//		String stra = encryptUtils.encryptToAES(keyAes, source);
//		System.out.println("AES加密后为:" + stra);
		// 使用这个密匙解密
		String stra = "664F942C4AD858C71D57642E47CD0DDB13368B3013AF53CD1D535D6C7CDC1E12";
		String strb = encryptUtils.decryptByAES(keyAes, stra);
		System.out.println(strb);
		String[] tokenArr = strb.split("\\.");
		System.out.println(tokenArr.length);
		System.out.println("AES解密后为：" + strb);
		System.out.println("========指定Key进行加解密==============");
	}
}
