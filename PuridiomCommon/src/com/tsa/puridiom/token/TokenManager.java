package com.tsa.puridiom.token;

import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

public class TokenManager {

	private String secret = "UnansweredBadgesTagsQuestions";
	private static TokenManager instance = null;

	protected TokenManager() {
	}

	public static TokenManager getInstance() {
		if (instance == null) {
			instance = new TokenManager();
		}
		return instance;
	}

	public String createToken(String id, String orgId, String userId) {
		return this.createJWT(id, orgId, userId);
	}
	

	public boolean verifyToken(String token) {
		return this.verifyJWT(token);
	}
	
	public boolean isUserTokenValid(String userTokenId, String sessionId, String organizationId, String userId) {
		boolean valid = false ;
		
		valid = TokenManager.getInstance().verifyToken(userTokenId) ;
		
		String testTokenId = this.createToken(sessionId, organizationId, userId) ;
		if (valid) valid = userTokenId.equals(testTokenId) ;

		return valid ;
	}

	
	public Map getTokenValues(String token) {
		return this.parseJWT(token) ;
	}

	private String createJWT(String id, String organizationId, String userId) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		Map payloadMap = new HashMap();
		payloadMap.put("id", id);
		payloadMap.put("organizationId", organizationId) ;
		payloadMap.put("userId", userId);

		byte[] secretKey = DatatypeConverter.parseBase64Binary(this.secret);

		// set the JWT Claims
		JwtBuilder builder = Jwts.builder().setClaims(payloadMap)
				.signWith(signatureAlgorithm, secretKey);

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	// method to validate and read the JWS
	private boolean verifyJWT(String jws) {
		// This line will throw an exception if it is not a signed JWS (as
		// expected)
		boolean ok = true;
		try {
			Jws<Claims> claims = Jwts
					.parser()
					.setSigningKey(
							DatatypeConverter.parseBase64Binary(this.secret))
					.parseClaimsJws(jws);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ok = false;
		}

		return ok;
	}
	
	private Map parseJWT(String jws) {
		// This line will throw an exception if it is not a signed JWS (as
		// expected)
		Map result = null ;
		try {
			Jws<Claims> claims = Jwts
					.parser()
					.setSigningKey(
							DatatypeConverter.parseBase64Binary(this.secret))
					.parseClaimsJws(jws);
			result = claims.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result ;
	}
	
}
