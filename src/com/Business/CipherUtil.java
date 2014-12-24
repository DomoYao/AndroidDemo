package com.Business;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 可逆加解密类。
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class CipherUtil {
	private static final Log4AK log = Log4AK.getLog(CipherUtil.class);
	public static final String ALGORITHM_DES = "DES";

	/**
	 * 返回可逆算法DES的密钥
	 * 
	 * @param key
	 *            前8字节将被用来生成密钥。
	 * @return 生成的密钥
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
	 * 根据指定的密钥及算法，将字符串进行解密。
	 * 
	 * @param data
	 *            要进行解密的数据，它是由原来的byte[]数组转化为字符串的结果。
	 * @param key
	 *            密钥。
	 * @param algorithm
	 *            算法。
	 * @return 解密后的结果。它由解密后的byte[]重新创建为String对象。如果解密失败，将返回null。
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
	 * 根据指定的密钥及算法对指定字符串进行可逆加密。
	 * 
	 * @param data
	 *            要进行加密的字符串。
	 * @param key
	 *            密钥。
	 * @param algorithm
	 *            算法。
	 * @return 加密后的结果将由byte[]数组转换为16进制表示的数组。如果加密过程失败，将返回null。
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