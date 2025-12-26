package com.gnf.qrest.tools;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLTool {

	public static void configureSSL() {
		try {
			SSLContext sslCtx = SSLContext.getInstance("TLSv1.2");
			TrustManager[] trustMgr = getTrustMgr();
			sslCtx.init(null, trustMgr, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sslCtx.getSocketFactory());

			HostnameVerifier allHostsValid = new HostnameVerifier() {
	            @Override
				public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        };
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static TrustManager[] getTrustMgr() {
		TrustManager[] certs = new TrustManager[] {
			new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				@Override
				public void checkClientTrusted(X509Certificate[] certs, String type) { }
				@Override
				public void checkServerTrusted(X509Certificate[] certs, String type) { }
			}
		};
		return certs;
	}

}
