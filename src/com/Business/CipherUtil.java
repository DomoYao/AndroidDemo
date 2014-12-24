package com.Business;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * ����ӽ����ࡣ
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class CipherUtil {
	private static final Log4AK log = Log4AK.getLog(CipherUtil.class);
	public static final String ALGORITHM_DES = "DES";

	/**
	 * ���ؿ����㷨DES����Կ
	 * 
	 * @param key
	 *            ǰ8�ֽڽ�������������Կ��
	 * @return ���ɵ���Կ
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static Key getDESKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException {
		DESKeySpec des = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
		return keyFactory.generateSecret(des);
	}

	/**
	 * ����ָ������Կ���㷨�����ַ������н��ܡ�
	 * 
	 * @param data
	 *            Ҫ���н��ܵ����ݣ�������ԭ����byte[]����ת��Ϊ�ַ����Ľ����
	 * @param key
	 *            ��Կ��
	 * @param algorithm
	 *            �㷨��
	 * @return ���ܺ�Ľ�������ɽ��ܺ��byte[]���´���ΪString�����������ʧ�ܣ�������null��
	 */
	public static String decrypt(String data, Key key, String algorithm) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, key);
			String result = new String(cipher.doFinal(StringUtil.hexStringToByteArray(data)), "utf8");
			return result;
		} catch (Exception e) {
			log.w(e);
		}
		return null;
	}

	/**
	 * ����ָ������Կ���㷨��ָ���ַ������п�����ܡ�
	 * 
	 * @param data
	 *            Ҫ���м��ܵ��ַ�����
	 * @param key
	 *            ��Կ��
	 * @param algorithm
	 *            �㷨��
	 * @return ���ܺ�Ľ������byte[]����ת��Ϊ16���Ʊ�ʾ�����顣������ܹ���ʧ�ܣ�������null��
	 */
	public static String encrypt(String data, Key key, String algorithm) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return StringUtil.byteArrayToHexString(cipher.doFinal(data.getBytes("utf8")));
		} catch (Exception e) {
			log.w(e);
		}
		return null;
	}
}