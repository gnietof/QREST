package com.gnf.qrest.qiskit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gnf.qrest.QiskitRuntimeService;
import com.gnf.qrest.model.Backend;
import com.gnf.qrest.model.PrimitiveRequest;
import com.gnf.qrest.model.SamplerPUB;
import java.util.List;


/**
 * Sampler primitive implementation.
 */
public class Sampler extends Primitive<SamplerPUB> {
  private static final ObjectMapper om = JsonMapper.builder()
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
      .serializationInclusion(JsonInclude.Include.NON_NULL)
      .build();

  /**
   * Sampler constructor for backend.
   * 
   * @param backend The backend to run in.
   */
  public Sampler(Backend backend) {
    super(backend);
  }

  @Override
  public Job run(SamplerPUB pub) {
    return run(List.of(pub));
  }

  @Override
  public Job run(List<SamplerPUB> pubs) {
    QiskitRuntimeService service = QiskitRuntimeService.getInstance();

    SamplerRequest req = new SamplerRequest(getBackend().getName(), pubs);

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
   * Models a SamplerRequest.
   */
  public static class SamplerRequest extends PrimitiveRequest {

    /**
     * SamplerRequest constructor for backend and pubs.
     * 
     * @param backend The backend to run in.
     * @param pubs The pubs to use for the sampler.
     */
    public SamplerRequest(String backend, List<SamplerPUB> pubs) {
      super(backend, pubs, "sampler");
    }

  }

}
