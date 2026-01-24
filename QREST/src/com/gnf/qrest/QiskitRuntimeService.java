package com.gnf.qrest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gnf.qrest.authentication.Token;
import com.gnf.qrest.model.Backend;
import com.gnf.qrest.model.BackendConfig;
import com.gnf.qrest.model.BackendProps;
import com.gnf.qrest.model.BackendStatus;
import com.gnf.qrest.model.Backends;
import com.gnf.qrest.model.BackendsRequest;
import com.gnf.qrest.model.PrimitiveRequest;
import com.gnf.qrest.model.PrimitiveResults;
import com.gnf.qrest.model.QResponse;
import com.gnf.qrest.model.Tags;
import com.gnf.qrest.model.Workloads;
import com.gnf.qrest.qiskit.Job;
import com.gnf.qrest.qiskit.Jobs;
import com.gnf.qrest.qiskit.Session;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;

public class QiskitRuntimeService {

  private static final ObjectMapper om = JsonMapper.builder()
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
      .serializationInclusion(JsonInclude.Include.NON_NULL)
      .build();
  private static final String API = "https://quantum.cloud.ibm.com/api/v1";
  private Token token;
  private static QiskitRuntimeService instance = new QiskitRuntimeService();

  public static QiskitRuntimeService getInstance() {
    return instance;
  }

  public Workloads workloads() {

    Workloads res = callREST("/workloads", "GET", null, null, Workloads.class);
    return res;

  }

  public Backend backend(String id) {
    List<Backend> res = backends(new BackendsRequest.Builder().name(id).build());
    if (res != null && res.size() > 0) {
      return res.get(0);
    }
    return null;
  }

  public BackendStatus backendStatus(String name) {
    BackendStatus res = callREST("/backends/" + name + "/status", 
        "GET", null, null, BackendStatus.class);
    return res;
  }

  public BackendProps backendProps(String name) {
    BackendProps res = callREST("/backends/" + name + "/properties", 
        "GET", null, null, BackendProps.class);
    return res;
  }

  public BackendConfig backendConfig(String name) {
    BackendConfig res = callREST("/backends/" + name + "/configuration", 
        "GET", null, null, BackendConfig.class);
    return res;
  }

  public List<Backend> backends(BackendsRequest request) {

    Backends res = callREST("/backends", "GET", null, null, Backends.class);

    List<Backend> devs = res.getDevices();

    try {
      devs = devs.stream().filter(d -> request.getName() == null 
          || d.getName().equals(request.getName()))
          .filter(d -> request.getMinNumQubits() == null 
          || d.getQubits() >= request.getMinNumQubits())
          .collect(Collectors.toList());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return devs;
  }

  public Job createJob(PrimitiveRequest req) {

    try {
      Job res = callREST("/jobs", "POST", null, om.writeValueAsString(req), Job.class);
      return res;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Job job(String id) {
    Job res = job(id, false);
    return res;
  }

  public Job job(String id, boolean excludeParams) {
    Job res = callREST("/jobs/" + id, "GET", "exclude_params=" + excludeParams, null, Job.class);
    return res;
  }

  public Jobs jobs() {
    Jobs res = callREST("/jobs", "GET", null, null, Jobs.class);
    return res;
  }

  public QResponse tags(String id, Tags tags) {
    try {
      QResponse res = callREST("/jobs/" + id + "/tags", 
          "PUT", null, om.writeValueAsString(tags), QResponse.class);
      return res;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Tags searchTags(String tag) {
    Tags tags = callREST("/tags", "GET", tag != null ? "search=" + tag : null, null, Tags.class);
    return tags;
  }

  public Session session(String id) {
    Session res = callREST("/sessions/" + id, "GET", null, null, Session.class);
    return res;
  }

  public Jobs sessionJobs(String id) {
    Jobs res = callREST("/jobs", "GET", "session_id=" + id, null, Jobs.class);
    return res;
  }

  public Job jobDetails(String id) {
    Job res = callREST("/jobs/" + id, "GET", null, null, Job.class);
    return res;
  }

  public PrimitiveResults jobResults(String id) {
    PrimitiveResults res = callREST("/jobs/" + id + "/results", 
        "GET", null, null, PrimitiveResults.class);
    return res;
  }

  public QResponse cancelJob(String id) {
    QResponse res = callREST("/jobs/" + id + "/cancel", 
        "POST", null, "", QResponse.class);
    return res;
  }

  public Job waitForFinalState(String id) {
    Job job = null;

    while (true) {
      job = job(id, true);
      System.out.println(id + ": " + job.getStatus());
      if (job.isInFinalState()) {
        break;
      }
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return job;
  }

  private String getToken() {
    String t = null;
    long d = new Date().getTime();
    if (token == null || d > (token.getExpiration() * 1000)) {
      doToken();
    }
    if (token != null) {
      t = token.getAccessToken();
    }
    return t;
  }

  private void doToken() {
    try {
      URL url = new URL("https://iam.cloud.ibm.com/oidc/token");
      HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
      uc.setRequestMethod("POST");
      uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      uc.setRequestProperty("Accept", "application/json");

      String apikey = System.getenv("IAMKEY");
      String data = "apikey=" + apikey + "&grant_type=urn:ibm:params:oauth:grant-type:apikey";

      uc.setDoOutput(true);
      OutputStream os = uc.getOutputStream();
      os.write(data.getBytes());

      int rc = uc.getResponseCode();
      if (rc == HttpURLConnection.HTTP_OK) {
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

  private <T> T callREST(String href, String method, String params, 
      String data, Class<T> c) {
    return callREST(href, method, params, data, c, false);
  }

  private <T> T callREST(String href, String method, String params, 
      String data, Class<T> c, boolean debug) {

    T o = null;

    try {
      String u = API + href + (params != null ? "?" + params : "");
      System.out.println("\nRequest: " + u);
      if (debug) {
        System.out.println("URL: [" + method + "] " + u);
      }
      URL url = new URL(u);
      HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
      uc.setRequestMethod(method);
      uc.setRequestProperty("Content-Type", "application/json");
      uc.setRequestProperty("Accept", "application/json");
      uc.setRequestProperty("Service-CRN", System.getenv("CRN"));
      uc.setRequestProperty("Authorization", "Bearer " + getToken());
      uc.setRequestProperty("IBM-API-Version", "2025-05-01");

      if (data != null) {
        uc.setDoOutput(true);
        OutputStream os = uc.getOutputStream();
        os.write(data.getBytes());
      }

      int rc = uc.getResponseCode();
      if (debug) {
        System.out.println("RC: " + rc);
      }
      switch (rc) {
        case HttpURLConnection.HTTP_OK:
        case HttpURLConnection.HTTP_CREATED:
        case HttpURLConnection.HTTP_NO_CONTENT:
    
          if (debug) {
            Map<String, List<String>> headers = uc.getHeaderFields();
            for (String key : headers.keySet()) {
              System.out.println(key + ": " + headers.get(key));
            }
          }
    
          InputStream is = uc.getInputStream();
          String s1 = new String(is.readAllBytes(), StandardCharsets.UTF_8);
    
          if (debug) {
            System.out.println("Output: " + s1);
          }
          if (!s1.isEmpty()) {
            if (c != null) {
              JavaType jt = om.getTypeFactory().constructType(c);
              o = om.readValue(s1, jt);
            }
          }
          break;
        default:
          InputStream es = uc.getErrorStream();
          if (es != null) {
            String s2 = new String(es.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(s2);
            if (!s2.isEmpty()) {
              if (c != null) {
                JavaType jt = om.getTypeFactory().constructType(c);
                o = om.readValue(s2, jt);
              }
            }
          }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return o;
  }

}
