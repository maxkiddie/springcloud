package com.ydy.utils;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 微信支付AES加解密工具类
 *
 * @author yclimb
 * @date 2018/6/21
 */
public class Aes7Util {

	/**
	 * 密钥算法
	 */
	private static final String ALGORITHM = "AES";

	/**
	 * 加解密算法/工作模式/填充方式
	 */
	private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";

	/**
	 * AES加密
	 *
	 * @param data d
	 * @return str
	 * @throws Exception e
	 */
	public static String encryptData(String data, String key) throws Exception {
		// 创建密码器
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
		// 初始化
		SecretKeySpec KEY = new SecretKeySpec(key.getBytes(), ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, KEY);
		return base64Encode8859(new String(cipher.doFinal(data.getBytes()), "ISO-8859-1"));

	}

	/**
	 * AES解密
	 *
	 * @param base64Data 64
	 * @return str
	 * @throws Exception e
	 */
	public static String decryptData(String base64Data, String key) throws Exception {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
		SecretKeySpec KEY = new SecretKeySpec(key.getBytes(), ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, KEY);
		return new String(cipher.doFinal(base64Decode8859(base64Data).getBytes("ISO-8859-1")), "utf-8");
	}

	/**
	 * Base64解码
	 * 
	 * @param source base64 str
	 * @return str
	 */
	public static String base64Decode8859(final String source) {
		String result = "";
		final Base64.Decoder decoder = Base64.getDecoder();
		try {
			// 此处的字符集是ISO-8859-1
			result = new String(decoder.decode(source), "ISO-8859-1");
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Base64加密
	 * 
	 * @param source str
	 * @return base64 str
	 */
	public static String base64Encode8859(final String source) {
		String result = "";
		final Base64.Encoder encoder = Base64.getEncoder();
		byte[] textByte = null;
		try {
			// 注意此处的编码是ISO-8859-1
			textByte = source.getBytes("ISO-8859-1");
			result = encoder.encodeToString(textByte);
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

}