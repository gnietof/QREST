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

/**
 * Models a QiskitRuntimeService.
 */
public class QiskitRuntimeService {

  private static final ObjectMapper om = JsonMapper.builder()
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
      .serializationInclusion(JsonInclude.Include.NON_NULL).build();
  private static final String API = "https://quantum.cloud.ibm.com/api/v1";
  private Token token;
  private static QiskitRuntimeService instance = new QiskitRuntimeService();

  public static QiskitRuntimeService getInstance() {
    return instance;
  }

  /**
   * Retrieves all workloads executed in a IBM QPU's.
   *
   * @return The workloads.
   */
  public Workloads workloads() {

    Workloads res = callREST("/workloads", "GET", null, null, Workloads.class);
    return res;

  }

  /**
   * Retrieves a backend by its name.
   *
   * @return The backend.
   */
  public Backend backend(String name) {
    List<Backend> res = backends(new BackendsRequest.Builder().name(name).build());
    if (res != null && res.size() > 0) {
      return res.get(0);
    }
    return null;
  }

  /**
   * Retrieves a backend status by its name.
   *
   * @return The backend status.
   */
  public BackendStatus backendStatus(String name) {
    BackendStatus res = callREST("/backends/" + name + "/status", "GET", null, null,
        BackendStatus.class);
    return res;
  }

  /**
   * Retrieves a backend properties by its name.
   *
   * @return The backend properties.
   */
  public BackendProps backendProps(String name) {
    BackendProps res = callREST("/backends/" + name + "/properties", "GET", null, null,
        BackendProps.class);
    return res;
  }

  /**
   * Retrieves a backend config by its name.
   *
   * @return The backend config.
   */
  public BackendConfig backendConfig(String name) {
    BackendConfig res = callREST("/backends/" + name + "/configuration", "GET", null, null,
        BackendConfig.class);
    return res;
  }

  /**
   * Lists the backends which match the request.
   *
   * @param request The request to match.
   * @return The list of backends.
   */
  public List<Backend> backends(BackendsRequest request) {

    Backends res = callREST("/backends", "GET", null, null, Backends.class);

    List<Backend> devs = res.getDevices();

    try {
      devs = devs.stream()
          .filter(d -> request.getName() == null || d.getName().equals(request.getName()))
          .filter(
              d -> request.getMinNumQubits() == null || d.getQubits() >= request.getMinNumQubits())
          .collect(Collectors.toList());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return devs;
  }


  /**
   * Creates a job using a primitive.
   *
   * @param req The primitive request.
   * @return The created job.
   */
  public Job createJob(PrimitiveRequest req) {

    try {
      Job res = callREST("/jobs", "POST", null, om.writeValueAsString(req), Job.class);
      return res;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Retrieves a job by its id.
   *
   * @return The job.
   */
  public Job job(String id) {
    Job res = job(id, false);
    return res;
  }

  /**
   * Retrieves a job by its id.
   *
   * @return The job.
   */
  public Job job(String id, boolean excludeParams) {
    Job res = callREST("/jobs/" + id, "GET", "exclude_params=" + excludeParams, null, Job.class);
    return res;
  }

  /**
   * Retrieves all jobs executed in a IBM QPU's.
   *
   * @return The job.
   */
  public Jobs jobs() {
    Jobs res = callREST("/jobs", "GET", null, null, Jobs.class);
    return res;
  }

  /**
   * Adds tags to a job.
   *
   * @return The response for the request.
   */
  public QResponse tags(String id, Tags tags) {
    try {
      QResponse res = callREST("/jobs/" + id + "/tags", "PUT", null, om.writeValueAsString(tags),
          QResponse.class);
      return res;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Search and list the tags of jobs.
   *
   * @param tag The tag.
   * @return The tags.
   */
  public Tags searchTags(String tag) {
    Tags tags = callREST("/tags", "GET", tag != null ? "search=" + tag : null, null, Tags.class);
    return tags;
  }

  /**
   * Retrieves a session by its id.
   *
   * @param id The session id.
   * @return The session.
   */
  public Session session(String id) {
    Session res = callREST("/sessions/" + id, "GET", null, null, Session.class);
    return res;
  }

  /**
   * Retrieves the jobs in a session by its id.
   *
   * @param id The session id.
   * @return The jobs.
   */
  public Jobs sessionJobs(String id) {
    Jobs res = callREST("/jobs", "GET", "session_id=" + id, null, Jobs.class);
    return res;
  }

  /**
   * Retrieves the details of a job its id.
   *
   * @param id The job id.
   * @return The job.
   */
  public Job jobDetails(String id) {
    Job res = callREST("/jobs/" + id, "GET", null, null, Job.class);
    return res;
  }

  /**
   * Retrieves the primitive results of a job its id.
   *
   * @param id The job id.
   * @return The primitive results.
   */
  public PrimitiveResults jobResults(String id) {
    PrimitiveResults res = callREST("/jobs/" + id + "/results", "GET", null, null,
        PrimitiveResults.class);
    return res;
  }

  /**
   * Cancels a job by its id.
   *
   * @param id The job id.
   * @return The jobs.
   */
  public QResponse cancelJob(String id) {
    QResponse res = callREST("/jobs/" + id + "/cancel", "POST", null, "", QResponse.class);
    return res;
  }

  /**
   * Waits for the completion of a job its id.
   *
   * @param id The job id.
   * @return The job.
   */
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

  /**
   * Gets a token in IBM Quantum for the configured user.
   */
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

  /**
   * Retrieves the token from IBM Quantum.
   */
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

  /**
   * Calls a REST endpoint.
   *
   * @param <T>    The expected type of data for the results.
   * @param href   The URL for the endpoint.
   * @param method The method which will be used (GET, POST...).
   * @param params The parameters if any added to the request.
   * @param data   Any data included in the request.
   * @param c      The type of the expected results.
   * @return An instance of the class T with the response provided.
   */
  private <T> T callREST(String href, String method, String params, String data, Class<T> c) {
    return callREST(href, method, params, data, c, false);
  }

  /**
   * Calls a REST endpoint.
   *
   * @param <T>    The expected type of data for the results.
   * @param href   The URL for the endpoint.
   * @param method The method which will be used (GET, POST...).
   * @param params The parameters if any added to the request.
   * @param data   Any data included in the request.
   * @param c      The type of the expected results.
   * @param debug  Whether we want to display debug information or not.
   * @return An instance of the class T with the response provided.
   */
  private <T> T callREST(String href, String method, String params, String data, Class<T> c,
      boolean debug) {

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
