package com.microsoft.oauth2;

import com.microsoft.dto.oauth2.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OAuth2Service {

	@FormUrlEncoded
	@POST("{tenant}/oauth2/v2.0/token")
	public Call<TokenResponse> token(@Path("tenant") String tenant, @Field("client_id") String clientId,
			@Field("scope") String scope, @Field("redirect_uri") String redirectUri,
			@Field("grant_type") String grantType, @Field("client_secret") String clientSecret,
			@Field("code") String code);

	@FormUrlEncoded
	@POST("{tenant}/oauth2/v2.0/token")
	public Call<TokenResponse> refreshToken(@Path("tenant") String tenant, @Field("client_id") String clientId,
			@Field("scope") String scope, @Field("grant_type") String grantType,
			@Field("client_secret") String clientSecret, @Field("refresh_token") String refreshToken);

}
