package com.gnf.qrest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnf.qrest.model.JobDetails;
import com.gnf.qrest.model.JobResults;
import com.gnf.qrest.model.JobResults.Result;
import com.gnf.qrest.model.JobResults.Result.Data;
import com.gnf.qrest.model.JobResults.Result.Data.C;
import com.gnf.qrest.model.JobRunRequest;
import com.gnf.qrest.model.JobRunResponse;
import com.gnf.qrest.model.JobStateStatus;
import com.gnf.qrest.model.JobsListResponse;
import com.gnf.qrest.model.Token;


public class QTool {
	
	private Token token;
	private static final ObjectMapper om = new ObjectMapper()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	private static final String API = "https://quantum.cloud.ibm.com/api/v1";

	public static void main(String[] args) {
		QTool qt = new QTool();

//	Get a list of jobs
//		JobsResponse jjrr = qt.listJobs();
//		for (JobResponse jr: jjrr.getJobs()) {
//			String backend = jr.getBackend();
//			String created = jr.getCreated();
//			JobStatus status = jr.getStatus();
//			System.out.println(String.format("%s: %s - %s",created,backend,status.toString()));
//		}
		
//		String qasm = "OPENQASM 3.0;include \"stdgates.inc\";qubit[2] q;h q[0];cx q[0], q[1];";
//		qt.runJob("ibm_fez","sampler",qasm);
//		try {
//			FileInputStream fis = new FileInputStream("/home/genaro/Documents/sample.qpy");
//			String qpy = new String(fis.readAllBytes(),StandardCharsets.UTF_8);
//			qt.runJob("ibm_fez","sampler",qpy);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
////		String qasm = "OPENQASM 3.0;include \"stdgates.inc\";qubit[2] q;h q[0];cx q[0], q[1];";
//		String qasm = "OPENQASM 3.0;include \"stdgates.inc\";bit[2] c;rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;c[0] = measure $0;c[1] = measure $1;";
////		String qasm = "OPENQASM 2.0;include \"qelib1.inc\";qreg q[133];creg c[2];rz(pi/2) q[0];sx q[0];rz(pi/2) q[0];rz(pi/2) q[1];sx q[1];rz(pi/2) q[1];cz q[0],q[1];rz(pi/2) q[1];sx q[1];rz(pi/2) q[1];measure q[0] -> c[0];measure q[1] -> c[1];";
////		qt.runJob("ibm_fez","sampler",qasm);
//		qt.runJob("ibm_torino","sampler",qasm);
		
//		String qasm = "OPENQASM 3.0;include \"stdgates.inc\";bit[2] c;rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;c[0] = measure $0;c[1] = measure $1;";
//		JobRunResponse res = qt.runJob("ibm_torino",qasm);
//		String id = res.getId();

		String qasm = "OPENQASM 3.0;include \"stdgates.inc\";bit[2] c;rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;c[0] = measure $0;c[1] = measure $1;";
		JobRunResponse res = qt.runJob("ibm_torino",qasm,Arrays.asList("XX"));
		String id = res.getId();
//
		JobStateStatus status = null;
		do {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			JobDetails jd = qt.getJobDetails(id);
			status = jd.getState().getStatus();
			System.out.println(id+": "+status);
		} while (EnumSet.of(JobStateStatus.Queued, JobStateStatus.Running).contains(status));

		JobResults jr = qt.getJobResults(id);
//		JobResults jr = qt.getJobResults("d4s30g4fitbs739ih7tg");
		List<Result> results = jr.getResults();
		Result result = results.get(0);
//		Data data = result.getData();
//		C c= data.getC();
//		List<String> samples = c.getSamples();
//		System.out.println("Results: "+samples.size());
		
		Double evs = result.getData().getEvs().get(0);
		System.out.println("EVS: "+evs);
		boolean enable =jr.getMetadata().getTwirling().isEnableGates();
		System.out.println("twirling.enable_gates: "+enable);
		

//		JobResults jr = qt.getJobResults("d4ruk5kfitbs739icpsg");
//		List<Result> results = jr.getResults();
//		Result result = results.get(0);
//		Data data = result.getData();
//		C c= data.getC();
//		List<String> samples = c.getSamples();
//		System.out.println("Results: "+samples.size());
		
	}
	
	public QTool() {
	}
	
	public JobRunResponse runJob(String backend,String qasm) {
		
		JobRunResponse res = null;
		try {
			JobRunRequest req = new JobRunRequest(backend,qasm);
			
			String pretty = om.writerWithDefaultPrettyPrinter().writeValueAsString(req);
			System.out.println(pretty);
			
			res = callREST("/jobs", "POST", null, om.writeValueAsString(req), JobRunResponse.class);
			System.out.println(res.getId());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	public JobRunResponse runJob(String backend,String qasm,List<String> observables) {
		
		JobRunResponse res = null;
		try {
			JobRunRequest req = new JobRunRequest(backend,qasm,observables);
			
			String pretty = om.writerWithDefaultPrettyPrinter().writeValueAsString(req);
			System.out.println(pretty);
			
			res = callREST("/jobs", "POST", null, om.writeValueAsString(req), JobRunResponse.class);
			System.out.println(res.getId());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	public JobResults getJobResults(String id) {
		
		JobResults res = callREST("/jobs/"+id+"/results", "GET", null, null, JobResults.class);
		return res;
		
	}
	
	public JobDetails getJobDetails(String id) {
		
		JobDetails res = callREST("/jobs/"+id, "GET", null, null, JobDetails.class);
		return res;
		
	}
	
	public JobsListResponse listJobs() {
		JobsListResponse res = callREST("/jobs", "GET", null, null, JobsListResponse.class);
		
		return res;
	}
	
	private String getToken() {
		String t =null;
		long d = new Date().getTime();
		if (token==null || d>(token.getExpiration()*1000)) {
			doToken();
		}
		if (token!=null) {
			t = token.getAccessToken();
		}
		return t;
	}

	private void doToken() {
		try {
			URL url = new URL("https://iam.cloud.ibm.com/oidc/token");
			HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
			uc.setRequestMethod("POST");
			uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			uc.setRequestProperty("Accept","application/json");

			String apikey = System.getenv("IAMKEY");
			String data = "apikey="+apikey+"&grant_type=urn:ibm:params:oauth:grant-type:apikey";

			uc.setDoOutput(true);
			OutputStream os = uc.getOutputStream();
			os.write(data.getBytes());

			int rc = uc.getResponseCode();
			if (rc==HttpURLConnection.HTTP_OK) {
				InputStream is = uc.getInputStream();
				String s = new String(is.readAllBytes(), StandardCharsets.UTF_8);
				token = om.readValue(s, Token.class);
			} else {
				InputStream es = uc.getErrorStream();
				String s = new String(es.readAllBytes(), StandardCharsets.UTF_8);
				System.out.println(s);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public <T> T callREST(String href,String method,String params, String data, Class<T> c) {

		T o = null; 
		
		try {
			URL url = new URL(API+href+(params!=null? "?"+params:""));
			HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
			uc.setRequestMethod(method);
			uc.setRequestProperty("Content-Type","application/json");
			uc.setRequestProperty("Accept","application/json");
			uc.setRequestProperty("Service-CRN",System.getenv("CRN"));
			uc.setRequestProperty("Authorization","Bearer "+getToken());
			uc.setRequestProperty("Accept","IBM-API-Version: 2025-05-01");
			
			if (data!=null) {
				uc.setDoOutput(true);
				OutputStream os = uc.getOutputStream();
				os.write(data.getBytes());
			}
			
			int rc = uc.getResponseCode();
			if (rc==HttpURLConnection.HTTP_OK) {
				InputStream is = uc.getInputStream();
				String s = new String(is.readAllBytes(), StandardCharsets.UTF_8);
				System.out.println(s);
				JavaType jt = om.getTypeFactory().constructType(c);				
				o = om.readValue(s, jt); 
			} else {
				InputStream es = uc.getErrorStream();
				String s = new String(es.readAllBytes(), StandardCharsets.UTF_8);
				System.out.println(s);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return o;
	}

	
	
}
