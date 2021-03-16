package com.richman.common.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;


public class JwtHelper {

	public static final String JWT_ID = "098f6bcd4621d373cade4e832627b4f6";
	public static final String JWT_SECRET = "HnNhZYwY13pHZZlpcMPdmHuSAeXOxYPFQDzxiAH6Heb";
	public static final String JWT_NAME = "dear.com";

	public static long JWT_REFRESH_TTL = 3600 * 1000 * 24 * 7;

	/**
	 * 由字符串生成加密key
	 * 
	 * @return
	 */
	private static SecretKey generalKey() {
		String stringKey = JwtHelper.JWT_SECRET;
		byte[] encodedKey = Base64.encode(stringKey.getBytes()).getBytes();
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}

	public static Claims parseJWT(String jsonWebToken) {
		try {
			SecretKey key = generalKey();
			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jsonWebToken).getBody();
			return claims;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 将userId生产token
	 *
	 * @param userId
	 * @return
	 */
    public static String createJWT(String userId) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// 生成签名密钥
		SecretKey signingKey = generalKey();

		// 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").claim("unique_name", userId)
				.claim("userId", userId).setIssuer(JwtHelper.JWT_NAME).setAudience(JwtHelper.JWT_ID).setIssuedAt(now)
				.signWith(signatureAlgorithm, signingKey);
		// 添加Token过期时间
		if (JwtHelper.JWT_REFRESH_TTL >= 0) {
			long expMillis = nowMillis + JwtHelper.JWT_REFRESH_TTL;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp).setNotBefore(now);
		}

		// 生成JWT
		return builder.compact();
	}
}
