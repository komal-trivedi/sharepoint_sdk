package com.microsoft.tokenprovider;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.microsoft.client.oauth2.OAuth2Client;
import com.microsoft.dto.oauth2.TokenResponse;
import com.microsoft.oauth2.OAuth2CredentialProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SharepointClientProvider {

	private final String HOST = "localhost";
	private final int PORT = 8973;
	private final String SP_AUTH_START = "sharepoint-auth-start";
	private final String SP_AUTH_FINISH = "sharepoint-auth-finish";
	private final String START_URI = String.format("http://%s:%d/%s", HOST, PORT, SP_AUTH_START);
	private final String REDIRECT_URI = String.format("http://%s:%d/%s", HOST, PORT, SP_AUTH_FINISH);
	private final OAuth2Client client = new OAuth2Client();

	public String setUpAccessToken() throws IOException {

		AccessTokenHolder accessTokenHolder = AccessTokenHolder.getInstance();		
		Tomcat tomcat = createTomcat();
		accessTokenHolder.actionPerformed(false);
		try {
			tomcat.start();
			browse(START_URI);
			while (!accessTokenHolder.isUserActionPerformed()) {
			}
			log.info("User Action Performed: {}", accessTokenHolder.isUserActionPerformed());		
			tomcat.stop();
			tomcat.destroy();
		} catch (LifecycleException e) {
		}
		String accessToken = accessTokenHolder.getToken();

		return accessToken;
	}

	private Tomcat createTomcat() throws IOException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(PORT);
		Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
		HttpServlet startServlet = createStartServlet();
		HttpServlet finishServlet = createFinishServlet();
		Tomcat.addServlet(ctx, SP_AUTH_START, startServlet);
		Tomcat.addServlet(ctx, SP_AUTH_FINISH, finishServlet);
		ctx.addServletMapping("/" + SP_AUTH_START, SP_AUTH_START);
		ctx.addServletMapping("/" + SP_AUTH_FINISH, SP_AUTH_FINISH);
		return tomcat;
	}

	@SuppressWarnings("serial")
	private HttpServlet createFinishServlet() throws IOException {
		return new HttpServlet() {
			@Override
			protected void service(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

				String code = request.getParameter("code");
				AccessTokenHolder tokenHolder = AccessTokenHolder.getInstance();
				log.info("Code: {}", code);
				TokenResponse tokenResponse = client.getToken(tokenHolder.getTenantId(), tokenHolder.getClientId(),
						tokenHolder.getScope(), REDIRECT_URI, "authorization_code", tokenHolder.getClientSecret(),
						code);
				tokenHolder.actionPerformed(true);
				tokenHolder.setToken(tokenResponse.getAccessToken());
				tokenHolder.setRefreshToken(tokenResponse.getRefreshToken());
				log.info("Access Token: {}", tokenResponse.getAccessToken());
				response.getWriter().print("Success!\nYou can close this window.");

			}
		};
	}

	@SuppressWarnings("serial")
	private HttpServlet createStartServlet() throws IOException {
		return new HttpServlet() {
			@Override
			protected void service(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

				String authorizePageUrl = null;
				try {
					AccessTokenHolder tokenHolder = AccessTokenHolder.getInstance();
					authorizePageUrl = OAuth2CredentialProvider.getAuthorizeUrl(tokenHolder.getTenantId(),
							tokenHolder.getClientId(), REDIRECT_URI, tokenHolder.getScope(), tokenHolder.getState());
					log.info("Authorize Url: {}", authorizePageUrl);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect(authorizePageUrl);
			}
		};
	}

	private void browse(String url) throws IOException, InternalError {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.BROWSE)) {

				desktop.browse(URI.create(url));
			}
		}
	}
}
