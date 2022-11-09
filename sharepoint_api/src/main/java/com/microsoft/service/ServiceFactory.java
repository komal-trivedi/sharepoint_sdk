package com.microsoft.service;

import java.io.IOException;

import com.microsoft.client.oauth2.OAuth2Client;
import com.microsoft.dto.oauth2.TokenResponse;
import com.microsoft.tokenprovider.AccessTokenHolder;
import com.microsoft.tokenprovider.TokenManager;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Slf4j
public class ServiceFactory {

	private static final String BASE_URL = "https://graph.microsoft.com/";

	private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
			.addConverterFactory(JacksonConverterFactory.create());

	private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

	private static OAuth2Client client = new OAuth2Client();

	private static String token = "eyJ0eXAiOiJKV1QiLCJub25jZSI6ImRub21nVDl4M3AtazdiUVJIMFhGdS1CdEFZVW1ra0lWbVRLWWp2UlN4WlUiLCJhbGciOiJSUzI1NiIsIng1dCI6IjJaUXBKM1VwYmpBWVhZR2FYRUpsOGxWMFRPSSIsImtpZCI6IjJaUXBKM1VwYmpBWVhZR2FYRUpsOGxWMFRPSSJ9.eyJhdWQiOiJodHRwczovL2dyYXBoLm1pY3Jvc29mdC5jb20iLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC80ZDdlNGI2ZS1hODcxLTQ2YTEtYTc1Yi01MjYxZTdkYWExMzUvIiwiaWF0IjoxNjY3MzkxODE0LCJuYmYiOjE2NjczOTE4MTQsImV4cCI6MTY2NzM5NjYxNiwiYWNjdCI6MCwiYWNyIjoiMSIsImFpbyI6IkFWUUFxLzhUQUFBQThnVXF0NjVpWjFvU0tCVmsrUnQvUS8wUlprdmJjNmNqWEFSa25vem05VUJPQTVNL3VKUzJFOXV3bzdFbk9US3FUNEhUMitGdVpBelVhdHFVY0JjcnlRLzhJSlRkTUdmOUxPQ0Nybnk3M20wPSIsImFtciI6WyJwd2QiLCJtZmEiXSwiYXBwX2Rpc3BsYXluYW1lIjoicG9zdG1hbl9tdWx0aV90ZW5lbnQiLCJhcHBpZCI6IjkyYzJhMGQ1LTI5NDktNDNhYi05OTQ5LWQ5MTYwYzhlYzcwNCIsImFwcGlkYWNyIjoiMSIsImZhbWlseV9uYW1lIjoiSHVsdGdyZW4iLCJnaXZlbl9uYW1lIjoiVG9tYXMiLCJpZHR5cCI6InVzZXIiLCJpcGFkZHIiOiI0My4yNDEuMTk0LjE0MiIsIm5hbWUiOiJUb21hcyBIdWx0Z3JlbiIsIm9pZCI6ImFkYWJjZDhlLTc5ZmYtNDMxNC1hYWQzLTczNTE0Y2QyMjc3ZCIsInBsYXRmIjoiMyIsInB1aWQiOiIxMDAzMjAwMjM0ODY1OUZDIiwicmgiOiIwLkFZSUFia3QtVFhHb29VYW5XMUpoNTlxaE5RTUFBQUFBQUFBQXdBQUFBQUFBQUFDVkFEWS4iLCJzY3AiOiJNYWlsLlJlYWQgU2l0ZXMuUmVhZFdyaXRlLkFsbCBVc2VyLlJlYWQgVXNlci5SZWFkV3JpdGUuQWxsIHByb2ZpbGUgb3BlbmlkIGVtYWlsIiwic2lnbmluX3N0YXRlIjpbImttc2kiXSwic3ViIjoiOC00LUpYQmFWWlY4bEdQSTR1VHVHcE90aWlTLTNlSnVvUjRwR090bTRMSSIsInRlbmFudF9yZWdpb25fc2NvcGUiOiJFVSIsInRpZCI6IjRkN2U0YjZlLWE4NzEtNDZhMS1hNzViLTUyNjFlN2RhYTEzNSIsInVuaXF1ZV9uYW1lIjoiVG9tYXNAQ29udGVudE1hcC5vbm1pY3Jvc29mdC5jb20iLCJ1cG4iOiJUb21hc0BDb250ZW50TWFwLm9ubWljcm9zb2Z0LmNvbSIsInV0aSI6InBBNndwRlI0MEU2c2dOWGM0MFF6QUEiLCJ2ZXIiOiIxLjAiLCJ3aWRzIjpbIjYyZTkwMzk0LTY5ZjUtNDIzNy05MTkwLTAxMjE3NzE0NWUxMCIsImI3OWZiZjRkLTNlZjktNDY4OS04MTQzLTc2YjE5NGU4NTUwOSJdLCJ4bXNfc3QiOnsic3ViIjoiTkozN01xeDdlal9JRVF2ekNodzBxLXRqNzVsYllFYzgteWJfNFRfR2ItUSJ9LCJ4bXNfdGNkdCI6MTY2NDE0MTk1MiwieG1zX3RkYnIiOiJFVSJ9.FVflHRuZKRZpIimaWZ2wncisfrHfLRkk1krOmEiztAR8f5H6pJJeMYkf2pJYsG-LrzdxH9G8TyzasewIQdrQMyatgeSxKLn8HtWNp3z-5ux7QP_m5gb5Hpcsp6ePykBHogZ0VbiMkApo_98uBbfpk2ErOhv6iKW2Q-uT_luhliCaWG_V176xaK9BzrbWw0zLqyAFK07kJjwiS7X41zVy94KdG24q83HjODIQzaauZDSlbSkuZxP9_IpiuXo4PsmBPO6XGGD_lEykF3fzb7Uj8BTPcI3LbLsBXDXFUVBKJidsiCuGiuTzZgkRFVINC896y2qojyXZsUDdgvuyldvxkQ";

	static {
		httpClient.addInterceptor(new Interceptor() {
			public Response intercept(Chain chain) throws IOException {
				Request original = chain.request();
				AccessTokenHolder tokenHolder = AccessTokenHolder.getInstance();
				if (tokenHolder.getToken() != null) {
					token = AccessTokenHolder.getInstance().getToken();
				}

				boolean isValid = TokenManager.isValidToken(token);
				log.info("Is Valid Token: {}", isValid);

				if (!isValid) {
					log.info("Invalid Token");
					TokenResponse tokenResponse = client.getRefreshToken(tokenHolder.getTenantId(),
							tokenHolder.getClientId(), tokenHolder.getScope(), "refresh_token",
							tokenHolder.getClientSecret(), AccessTokenHolder.getInstance().getRefreshToken());
					tokenHolder.setToken(tokenResponse.getAccessToken());
					tokenHolder.setRefreshToken(tokenResponse.getRefreshToken());
					token = tokenResponse.getAccessToken();
				}

				// Request customization: add request headers
				Request.Builder requestBuilder = original.newBuilder().header("Authorization", "Bearer " + token)
						.method(original.method(), original.body()).url(original.url());

				Request request = requestBuilder.build();
				return chain.proceed(request);
			}
		});
	}

	private static Retrofit retrofit = builder.client(httpClient.build()).build();

	/**
	 * creates the instance of the provided service
	 * 
	 * @param <S>
	 * @param serviceClass class parameter that lists the apis
	 * @return the service instance for the given service class
	 */
	public static <S> S createService(Class<S> serviceClass) {

		return retrofit.create(serviceClass);
	}
}
