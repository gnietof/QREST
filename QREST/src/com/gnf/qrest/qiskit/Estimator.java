package com.gnf.qrest.qiskit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gnf.qrest.QiskitRuntimeService;
import com.gnf.qrest.model.Backend;
import com.gnf.qrest.model.EstimatorPUB;
import com.gnf.qrest.model.PrimitiveRequest;
import java.util.List;

/**
 * Estimator primitive implementation.
 */
public class Estimator extends Primitive<EstimatorPUB> {
  private static final ObjectMapper om = JsonMapper.builder()
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
      .serializationInclusion(JsonInclude.Include.NON_NULL).build();

  /**
   * Estimator constructor for backend.
   *
   * @param backend The backend to run in.
   */
  public Estimator(Backend backend) {
    super(backend);
  }

  @Override
  public Job run(EstimatorPUB pub) {
    return run(List.of(pub));
  }

  @Override
  public Job run(List<EstimatorPUB> pubs) {
    QiskitRuntimeService service = QiskitRuntimeService.getInstance();

    EstimatorRequest req = new EstimatorRequest(getBackend().getName(), pubs);

    try {
      String pretty = om.writerWithDefaultPrettyPrinter().writeValueAsString(req);
      System.out.println(pretty);

      Job res = service.createJob(req);
      return res;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Models a EstimatorRequest.
   */
  public static class EstimatorRequest extends PrimitiveRequest {

    /**
     * EstimatorRequest constructor for backend and pubs.
     *
     * @param backend The backend to run in.
     * @param pubs    The pubs to use for the sampler.
     */
    public EstimatorRequest(String backend, List<EstimatorPUB> pubs) {
      super(backend, pubs, "estimator");
    }

  }

}
