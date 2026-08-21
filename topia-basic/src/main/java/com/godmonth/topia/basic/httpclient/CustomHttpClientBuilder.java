package com.godmonth.topia.basic.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.Credentials;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.impl.routing.DefaultProxyRoutePlanner;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.core.io.Resource;

public class CustomHttpClientBuilder {

	protected Resource certResource;

	protected String certPassword;

	private String proxyAddress;

	private String proxyUsername;

	private String proxyPassword;

	public CloseableHttpClient build() throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException,
			KeyManagementException, UnrecoverableKeyException {
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		if (certResource != null) {

			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			InputStream inputStream = certResource.getInputStream();
			keyStore.load(inputStream, certPassword.toCharArray());
			inputStream.close();

			SSLContext sslcontext = SSLContextBuilder.create()
					.loadKeyMaterial(keyStore, certPassword.toCharArray())
					.build();
			SSLConnectionSocketFactory sslsf = SSLConnectionSocketFactoryBuilder.create()
					.setSslContext(sslcontext)
					.setTlsVersions("TLSv1")
					.setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
					.build();
			httpClientBuilder.setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
					.setSSLSocketFactory(sslsf)
					.build());
		}
		if (StringUtils.isNotBlank(proxyAddress)) {
			HttpHost httpHost = toHttpHost(proxyAddress);
			httpClientBuilder.setRoutePlanner(new DefaultProxyRoutePlanner(httpHost));
			if (StringUtils.isNotBlank(proxyUsername)) {
				AuthScope authScope = new AuthScope(httpHost);
				Credentials credentials = new UsernamePasswordCredentials(proxyUsername,
						proxyPassword != null ? proxyPassword.toCharArray() : null);
				BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
				credsProvider.setCredentials(authScope, credentials);
				httpClientBuilder.setDefaultCredentialsProvider(credsProvider);
			}
		}
		return httpClientBuilder.build();
	}

	private static HttpHost toHttpHost(String proxyAddress) {
		try {
			return HttpHost.create(proxyAddress);
		} catch (java.net.URISyntaxException e) {
			throw new IllegalArgumentException("Invalid proxy address: " + proxyAddress, e);
		}
	}

	public void setCertResource(Resource certResource) {
		this.certResource = certResource;
	}

	public void setCertPassword(String certPassword) {
		this.certPassword = certPassword;
	}

	public void setProxyAddress(String proxyAddress) {
		this.proxyAddress = proxyAddress;
	}

	public void setProxyUsername(String proxyUsername) {
		this.proxyUsername = proxyUsername;
	}

	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}

}
