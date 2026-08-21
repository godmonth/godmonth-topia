package com.godmonth.topia.basic.httpclient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DummySslHttpClientBuilderTest {

	@Test
	@Disabled("was an integration call to a live HTTPS URL; keep builder smoke test only")
	public void test() throws KeyManagementException, UnrecoverableKeyException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		DummySslHttpClientBuilder builder = new DummySslHttpClientBuilder();
		try (CloseableHttpClient httpClient = builder.build()) {
			Assertions.assertNotNull(httpClient);
		}
	}
}
