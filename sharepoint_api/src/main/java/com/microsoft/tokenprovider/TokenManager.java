package com.microsoft.tokenprovider;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenManager {

	public static boolean isValidToken(String token) {
		boolean isValid = true;
		DecodedJWT jwt = JWT.decode(token);
		Date expirationDate = jwt.getExpiresAt();
		log.info("Expiration Date: {}", expirationDate);
		Long expMinus5Mins = expirationDate.toInstant().minus(5, ChronoUnit.MINUTES).toEpochMilli();
		Date expDateMinus5 = new Date(expMinus5Mins);
		log.info("Expiration Date Minus 5 mins: {}", expDateMinus5);
		if (new Date(expMinus5Mins).before(Calendar.getInstance().getTime())) {
			isValid = false;
		}
		log.info("Is Valid: {}", isValid);

		return isValid;
	}
}
