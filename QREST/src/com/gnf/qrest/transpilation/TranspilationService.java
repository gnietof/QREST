package com.gnf.qrest.transpilation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gnf.qrest.model.Paulis;
import com.gnf.qrest.simulator.EstimatorRequest;
import com.gnf.qrest.simulator.EstimatorResponse;
import com.gnf.qrest.simulator.SamplerRequest;
import com.gnf.qrest.simulator.SamplerResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/**
 * Provides a service to access circuit transpilation.
 */
public final class TranspilationService {

  private static final ObjectMapper om = JsonMapper.builder()
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
      .serializationInclusion(JsonInclude.Include.NON_NULL)
      .build();
  private static final String TRANSPILATION = "https://www.gnf.es:8443";
  private static TranspilationService instance = new TranspilationService();

  /**
   * Retrieves an instance of the transpilation service.
   * 
   * @return Transpilation service instance
   */
  public static TranspilationService getInstance() {
    return instance;
  }

  /**
   * Execute the transpilation of the circuit for the provided backend.
   * 
   * @param backend The backend which will run the circuit.
   * @param circuit The circuit to run. 
   * @param level The optimization level (0-3).
   * @return The transpiled circuit.
   */
  public String transpile(String backend, String circuit, int level) {

    TranspileRequest req = new TranspileRequest();
    req.setBackend(backend);
    req.setCircuit(circuit);
    req.setOptimizationLevel(level);

    try {
      TranspileResponse res = callREST("/transpile", 
          "POST", null, om.writeValueAsString(req), TranspileResponse.class);
      if (res != null) {
        return res.getQASM();
      }
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Execute the a Sampler of the circuit in the provided backend.
   * 
   * @param backend The backend which will run the circuit.
   * @param circuit The circuit to run. 
   * @param shots The number of shots to execute
   * @return An array of primitive responses
   */
  public SamplerResponse sampler(String circuit, int shots) {

    SamplerRequest req = new SamplerRequest();
    req.setCircuit(circuit);
    req.setShots(shots);

    try {
      SamplerResponse res = callREST("/sampler",
          "POST", null, om.writeValueAsString(req), SamplerResponse.class);
      if (res != null) {
        return res;
      }
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Execute the an Estimator of the circuit for those observables in the 
   * provided backend.
   *  
   * @param backend The backend which will run the circuit.
   * @param circuit The circuit to run. 
   * @param observables The array of observables to use. 
   * @return An array of primitive responses
   */
  public EstimatorResponse estimator(String circuit, String observable) {

    EstimatorRequest req = new EstimatorRequest();
    req.setCircuit(circuit);
    req.setObservable(observable);

    try {
      EstimatorResponse res = callREST("/estimator", 
          "POST", null, om.writeValueAsString(req), EstimatorResponse.class);
      if (res != null) {
        return res;
      }
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Applies a layout to a list of observable based on the transpilation of 
   * the cirtuit.
   * 
   * @param backend The backend which will run the circuit.
   * @param circuit The ciruit to traspile.
   * @param observables The array of observables to apply the circuit layout. 
   * @param level The optimization level (0-3).
   * @return The observables having the layout of the transpiled cirtuit 
   * being applied.
   */
  public LayoutResponse layout(String backend, String circuit, 
      List<Paulis> observables, int level) {

    LayoutRequest req = new LayoutRequest();
    req.setBackend(backend);
    req.setCircuit(circuit);
    req.setObservables(observables);
    req.setOptimizationLevel(level);

    try {
      LayoutResponse res = callREST("/layout", 
          "POST", null, om.writeValueAsString(req), LayoutResponse.class);
      if (res != null) {
        return res;
      }
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * @param circuit
   * @param os
   */
  public void draw(String circuit, OutputStream os) {

    CircuitRequest req = new CircuitRequest();
    req.setCircuit(circuit);

    try (InputStream is = callREST("/draw", 
        "POST", null, om.writeValueAsString(req))) {
      is.transferTo(os);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Calls a REST endpoint.
   * 
   * @param <T> The expected type of data for the results.
   * @param href The URL for the endpoint.
   * @param method The method which will be used (GET, POST...).
   * @param params The parameters if any added to the request.
   * @param data Any data included in the request.
   * @param c The type of the expected results.
   * @return An instance of the class T with the response provided. 
   */
  private <T> T callREST(String href, String method, 
      String params, String data, Class<T> c) {
    return callREST(href, method, params, data, c, false);
  }

  /**
   * Calls a REST endpoint.
   * 
   * @param <T> The expected type of data for the results.
   * @param href The URL for the endpoint.
   * @param method The method which will be used (GET, POST...).
   * @param params The parameters if any added to the request.
   * @param data Any data included in the request.
   * @param c The type of the expected results.
   * @param debug Whether we want to display debug information or not.
   * @return An instance of the class T with the response provided. 
   */
  private <T> T callREST(String href, String method, 
      String params, String data, Class<T> c, boolean debug) {

    T o = null;

    try {
      String u = TRANSPILATION + href + (params != null ? "?" + params : "");
      if (debug) {
        System.out.println("URL: " + u);
      }
      URL url = new URL(u);
      HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
      uc.setRequestMethod(method);
      uc.setRequestProperty("Content-Type", "application/json");
      uc.setRequestProperty("Accept", "application/json");

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
          }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return o;
  }

  /**
   * @param href
   * @param method
   * @param params
   * @param data
   * @return
   */
  private InputStream callREST(String href, String method, 
      String params, String data) {
    return callREST(href, method, params, data, false);
  }

  /**
   * @param href
   * @param method
   * @param params
   * @param data
   * @param debug
   * @return
   */
  private InputStream callREST(String href, String method, 
      String params, String data, boolean debug) {

    try {
      String u = TRANSPILATION + href + (params != null ? "?" + params : "");
      if (debug) {
        System.out.println("URL: " + u);
      }
      URL url = new URL(u);
      HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
      uc.setRequestMethod(method);
      uc.setRequestProperty("Content-Type", "application/json");

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
          InputStream is = uc.getInputStream();
          return is;
        default:
          InputStream es = uc.getErrorStream();
          if (es != null) {
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
