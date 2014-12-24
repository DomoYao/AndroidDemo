package com.Business;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 摘要加密算法类。
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class DigestUtil {
	private static final Log4AK log = Log4AK.getLog(DigestUtil.class);

	/**
	 * 获取一个字符串加密后的16进制值
	 * 
	 * @param algorithm
	 *            摘要算法
	 * @param message
	 *            进行加密的String对象
	 * 
	 * @return String 计算后的结果
	 */
	public static String doDigest(String algorithm, String message) {
		return doDigest(algorithm, message.getBytes());
	}

	/**
	 * 获取一个字符串加密后的16进制值
	 * 
	 * @param algorithm
	 *            摘要算法
	 * @param message
	 *            进行加密的byte数组
	 * 
	 * @return String 计算后的结果
	 */
	public static String doDigest(String algorithm, byte[] message) {
		try {
			final MessageDigest md = MessageDigest.getInstance(algorithm);
			if (message != null) {
				byte[] result = md.digest(message);
				return StringUtil.byteArrayToHexString(result);
			}
		} catch (NoSuchAlgorithmException e) {
			log.w(e);
		}
		return null;
	}

	/**
	 * 获取一个字符串加密后的16进制值
	 * 
	 * @param algorithm
	 *            摘要算法名称
	 * @param message
	 *            进行加密的byte数组
	 * 
	 * @return String 计算后的结果
	 */
	public static String doDigest(String algorithm, char[] message) {
		return doDigest(algorithm, new String(message));
	}
}