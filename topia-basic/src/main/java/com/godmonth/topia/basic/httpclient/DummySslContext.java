package com.godmonth.topia.basic.httpclient;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

public class DummySslContext {

	private DummySslContext() {

	}

	public static final SSLContext INSTANCE;

	static {
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
			X509TrustManager x509 = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { x509 }, null);
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}

		INSTANCE = ctx;
	}
}
