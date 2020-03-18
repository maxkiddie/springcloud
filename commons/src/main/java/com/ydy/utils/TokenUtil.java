/**
 * 
 */
package com.ydy.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ydy.model.User;

/**
 * @author nothing
 */
public class TokenUtil {
	private final static Logger log = LoggerFactory.getLogger(TokenUtil.class);

	/** token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfj */
	public static final String SECRET = "JKKLJOoasdlfj";
	/** token 过期时间: 10�? */
	public static final int calendarInterval = 30;

	private static final String USER_ID = "USER_ID";

	public static String createUserToken(User user) {
		Date iatDate = new Date();
		// expire time
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.DATE, calendarInterval);
		Date expiresDate = nowTime.getTime();

		// header Map
		Map<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");

		// build token
		// param backups {iss:Service, aud:APP}
		String token = JWT.create().withHeader(map) // header
				.withClaim(USER_ID, user.getId()).withIssuedAt(iatDate) // sign
				.withExpiresAt(expiresDate) // expire time
				.sign(Algorithm.HMAC256(SECRET)); // signature
		return token;
	}

	/**
	 * 解密Token
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Claim> verifyToken(String token) {
		if (token == null || "".equals(token)) {
			log.error("token是必要参数?");
			throw new RuntimeException("token是必要参数");
		}
		DecodedJWT jwt = null;
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
			jwt = verifier.verify(token);
		} catch (TokenExpiredException e) {
			log.error("token已过期");
			throw e;
		} catch (JWTDecodeException e) {
			log.error("token被篡改,格式错误");
			throw e;
		} catch (Exception e) {
			log.error("token错误:{}", e.getMessage());
			throw e;
		}
		return jwt.getClaims();
	}

	public static User getUser(String token) {
		Map<String, Claim> claims = verifyToken(token);
		User user = new User();
		user.setId(claims.get(USER_ID).asLong());
		return user;
	}

}
