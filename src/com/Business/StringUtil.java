package com.Business;

import java.util.Locale;


/**
 * String�����ࡣ
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class StringUtil {

	/**
	 * byte[]����ת��Ϊ16���Ƶ��ַ�����
	 * 
	 * @param data
	 *            Ҫת�����ֽ����顣
	 * @return ת����Ľ����
	 */
	public static final String byteArrayToHexString(byte[] data) {
		StringBuilder sb = new StringBuilder(data.length * 2);
		for (byte b : data) {
			int v = b & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase(Locale.getDefault());
	}

	/**
	 * 16���Ʊ�ʾ���ַ���ת��Ϊ�ֽ����顣
	 * 
	 * @param s
	 *            16���Ʊ�ʾ���ַ���
	 * @return byte[] �ֽ�����
	 */
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] d = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			// ��λһ�飬��ʾһ���ֽ�,��������ʾ��16�����ַ�������ԭ��һ�������ֽ�
			d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(
					s.charAt(i + 1), 16));
		}
		return d;
	}
}