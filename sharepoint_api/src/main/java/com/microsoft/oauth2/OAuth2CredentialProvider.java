package com.microsoft.oauth2;

import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

public class OAuth2CredentialProvider {

	public static String getAuthorizeUrl(String tenantId, String clientId, String redirectUri, String scope, String state)
			throws URISyntaxException {
		URIBuilder uribuilder = new URIBuilder("https://login.microsoftonline.com");
		uribuilder.addParameter("client_id", clientId);
		uribuilder.addParameter("response_type", "code");
		uribuilder.addParameter("redirect_uri", redirectUri);
		uribuilder.addParameter("response_mode", "query");
		uribuilder.addParameter("scope", scope);
		uribuilder.addParameter("state", "12351");
		uribuilder.setPath("/" + tenantId + "/oauth2/v2.0/authorize");
		return uribuilder.build().toString();
	}

}
