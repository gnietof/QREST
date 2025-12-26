package com.gnf.qrest.transpilation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gnf.qrest.model.Paulis;
import com.gnf.qrest.tools.SSLTool;

public class TranspilationService {

	private static final ObjectMapper om = JsonMapper.builder()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
			.serializationInclusion(JsonInclude.Include.NON_NULL)
			.build();
	private static final String TRANSPILATION = "https://www.gnf.es:8443";
	private static TranspilationService instance = new TranspilationService();

	
	public static TranspilationService getInstance() {
		return instance;
	}
	
	public String transpile(String backend, String circuit, int level) {
		
		TranspileRequest req = new TranspileRequest();
		req.setBackend(backend);
		req.setCircuit(circuit);
		req.setOptimizationLevel(level);
		
		try {
			TranspileResponse res = callREST("/transpile", "POST", null, om.writeValueAsString(req), TranspileResponse.class);
			if (res!=null) {
				return res.getQASM();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
//	public List<Paulis> layout(String backend, String circuit, List<Paulis> observables, int level) {
	public LayoutResponse layout(String backend, String circuit, List<Paulis> observables, int level) {
		
		LayoutRequest req = new LayoutRequest();
		req.setBackend(backend);
		req.setCircuit(circuit);
		req.setObservables(observables);
		req.setOptimizationLevel(level);
		
		try {
			LayoutResponse res = callREST("/layout", "POST", null, om.writeValueAsString(req), LayoutResponse.class);
			if (res!=null) {
				return res;
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void draw(String circuit,OutputStream os) {
		
		CircuitRequest req = new CircuitRequest();
		req.setCircuit(circuit);
		
		try (InputStream is = callREST("/draw", "POST", null, om.writeValueAsString(req))) {
			is.transferTo(os);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private <T> T callREST(String href,String method,String params, String data, Class<T> c) {
		return callREST(href, method, params, data,c,false);
	}
	
	private <T> T callREST(String href,String method,String params, String data, Class<T> c,boolean debug) {

		SSLTool.configureSSL();

		T o = null; 
		
		try {
			String u = TRANSPILATION+href+(params!=null? "?"+params:"");
			if (debug) {
				System.out.println("URL: "+u);
			}
			URL url = new URL(u);
			HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
			uc.setRequestMethod(method);
			uc.setRequestProperty("Content-Type","application/json");
			uc.setRequestProperty("Accept","application/json");
			
			if (data!=null) {
				uc.setDoOutput(true);
				OutputStream os = uc.getOutputStream();
				os.write(data.getBytes());
			}
			
			int rc = uc.getResponseCode();
			if (debug) {
				System.out.println("RC: "+rc);
			}
			switch (rc) {
				case HttpURLConnection.HTTP_OK:
				case HttpURLConnection.HTTP_CREATED:
				case HttpURLConnection.HTTP_NO_CONTENT:
					
					if (debug) {
						Map<String, List<String>> headers = uc.getHeaderFields();
						for (String key: headers.keySet()) {
							System.out.println(key+": "+headers.get(key));
						}
					}
					
					InputStream is = uc.getInputStream();
					String s1 = new String(is.readAllBytes(), StandardCharsets.UTF_8);
					
//					if (debug) {
						System.out.println("Output: "+s1);
//					}
					if (!s1.isEmpty()) {
						if (c!=null) {
							JavaType jt = om.getTypeFactory().constructType(c);				
							o = om.readValue(s1, jt);
						}
					}
					break;
				default:
					InputStream es = uc.getErrorStream();
					if (es!=null) {
						String s2 = new String(es.readAllBytes(), StandardCharsets.UTF_8);
						System.out.println(s2);
					}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return o;
	}
	
	private InputStream callREST(String href,String method,String params, String data) {
		return callREST(href, method, params, data,false);
	}
	
	private InputStream callREST(String href,String method,String params, String data, boolean debug) {
		SSLTool.configureSSL();

		try {
			String u = TRANSPILATION+href+(params!=null? "?"+params:"");
			if (debug) {
				System.out.println("URL: "+u);
			}
			URL url = new URL(u);
			HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
			uc.setRequestMethod(method);
			uc.setRequestProperty("Content-Type","application/json");
//			uc.setRequestProperty("Accept","application/json");
			
			if (data!=null) {
				uc.setDoOutput(true);
				OutputStream os = uc.getOutputStream();
				os.write(data.getBytes());
			}
			
			int rc = uc.getResponseCode();
			if (debug) {
				System.out.println("RC: "+rc);
			}
			switch (rc) {
				case HttpURLConnection.HTTP_OK:
//				case HttpURLConnection.HTTP_CREATED:
//				case HttpURLConnection.HTTP_NO_CONTENT:
					
					InputStream is = uc.getInputStream();
					return is;
				default:
					InputStream es = uc.getErrorStream();
					if (es!=null) {
						String s2 = new String(es.readAllBytes(), StandardCharsets.UTF_8);
						System.out.println(s2);
					}
					return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	
}
