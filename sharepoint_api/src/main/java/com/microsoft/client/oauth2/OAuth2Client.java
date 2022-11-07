package com.microsoft.client.oauth2;

import java.io.IOException;

import com.microsoft.dto.oauth2.TokenResponse;
import com.microsoft.oauth2.OAuth2Service;
import com.microsoft.oauth2.OAuth2ServiceFactory;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

@Slf4j
public class OAuth2Client {

	public TokenResponse getToken(String tenant, String clientId, String scope, String redirectUri, String grantType,
			String clientSecret, String code) throws IOException {

		OAuth2Service service = OAuth2ServiceFactory.createService(OAuth2Service.class);
		Call<TokenResponse> call = service.token(tenant, clientId, scope, redirectUri, grantType, clientSecret, code);
		Response<TokenResponse> response = call.execute();
		TokenResponse tokenResponse = null;
		if (!response.isSuccessful()) {
			log.info("Error: {}", response.errorBody().string());
		} else {
			tokenResponse = response.body();
			log.info("Response: {}", tokenResponse);
		}
		return tokenResponse;
	}

	public TokenResponse getRefreshToken(String tenant, String clientId, String scope, String grantType, String clientSecret,
			String refreshToken) throws IOException {

		OAuth2Service service = OAuth2ServiceFactory.createService(OAuth2Service.class);
		Call<TokenResponse> call = service.refreshToken(tenant, clientId, scope, grantType, clientSecret, refreshToken);
		Response<TokenResponse> response = call.execute();
		TokenResponse tokenResponse = null;
		
		if (!response.isSuccessful()) {
			log.info("Error: {}", response.errorBody().string());
		} else {
			tokenResponse = response.body();
			log.info("Response: {}", tokenResponse);
		}
		return tokenResponse;
	}

}
