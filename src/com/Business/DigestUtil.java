package com.Business;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ժҪ�����㷨�ࡣ
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class DigestUtil {
	private static final Log4AK log = Log4AK.getLog(DigestUtil.class);

	/**
	 * ��ȡһ���ַ������ܺ��16����ֵ
	 * 
	 * @param algorithm
	 *            ժҪ�㷨
	 * @param message
	 *            ���м��ܵ�String����
	 * 
	 * @return String �����Ľ��
	 */
	public static String doDigest(String algorithm, String message) {
		return doDigest(algorithm, message.getBytes());
	}

	/**
	 * ��ȡһ���ַ������ܺ��16����ֵ
	 * 
	 * @param algorithm
	 *            ժҪ�㷨
	 * @param message
	 *            ���м��ܵ�byte����
	 * 
	 * @return String �����Ľ��
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
	 * ��ȡһ���ַ������ܺ��16����ֵ
	 * 
	 * @param algorithm
	 *            ժҪ�㷨����
	 * @param message
	 *            ���м��ܵ�byte����
	 * 
	 * @return String �����Ľ��
	 */
	public static String doDigest(String algorithm, char[] message) {
		return doDigest(algorithm, new String(message));
	}
}