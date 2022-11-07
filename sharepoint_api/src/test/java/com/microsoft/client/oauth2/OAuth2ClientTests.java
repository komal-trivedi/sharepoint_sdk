package com.microsoft.client.oauth2;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.dto.oauth2.TokenResponse;

import junit.framework.Assert;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("deprecation")
@Slf4j
public class OAuth2ClientTests {

	OAuth2Client client;

	@BeforeEach
	public void tearUp() {
		client = new OAuth2Client();
	}

	@Test
	public void getAccessToken() throws IOException {
		TokenResponse accessToken = client.getToken("common", "92c2a0d5-2949-43ab-9949-d9160c8ec704",
				"https://graph.microsoft.com/User.ReadWrite.All", "http://localhost", "authorization_code",
				"HBI8Q~LTcWLI2STYn83PuOeUD~tdgU8QOmURwce2",
				"0.AYIAbkt-TXGooUanW1Jh59qhNdWgwpJJKatDmUnZFgyOxwSVADY.AgABAAIAAAD--DLA3VO7QrddgJg7WevrAgDs_wQA9P8ORCxxEJABzHqgazVzfmi4XDkL2jHm3XSR4TxmbGZ0Mq5r7Gcumy39qE8Jno-e5Ix_WfojJA6YMvVk-l3WzxOCz2NMNGq2Z9NLu6CFC_bg5Ra3_j8zX_MDYAk0slX7hRtvuuTTRMDk-Dgpcj1uRkLQCub-Aqrw2Ft71nGpdliUwmtslbZaPyHYgj6JDiUJlko2YagdK0kpPPNh3Yrq5kum7QzIYazzy2xPgGwuSlPiuNJ9e1S9MDxP1Uzdr4EI57o1SjuYRI4CtoUSjoi4z1s04MuWexEcjbPueKnP0XhJoJHyZHBTaWUDHF_nWUGO4ovUinhNtCkiiD-HzooH4t2RpiVRaVHTO13BBCNguv64L6T2_Z-gfsrjyJSd9bHyRKJO2weLPXbhrwo4IKfSn-1dZYYJycGsYNBJBTZpWjd5WxJ95MyWWpGcMYEAaO_fdV1wknI9LIwfDfxKTB_-sBDYt-lF2FfD3UqZnbJjAm0ubc3MfBMRuttAqeXbOUalWCC93gXJVeq-HllG_7xKMQThYbNR6Iva4N7imYZK9KjXkQg3GQtUKqHdvrAu1SqPRviGoKhbeTaqhQGGB88rRSdkNaqEqvML92jnW-380R_YsuuQtS2-EHzaHs-PtIwS4qMrd8QU6c92WeNNRcVh_--ESWPRY1fTHuz3yvEu78l2ndtiHQag8K12gkJY");
		log.info("Access Token: {}", accessToken);
		Assert.assertNotNull(accessToken);
	}
}
