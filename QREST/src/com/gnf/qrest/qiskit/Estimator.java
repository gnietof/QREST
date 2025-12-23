package com.gnf.qrest.qiskit;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnf.qrest.QiskitRuntimeService;
import com.gnf.qrest.builders.EstimatorPUB;
import com.gnf.qrest.model.Backend;
import com.gnf.qrest.model.PrimitiveRequest;
import com.gnf.qrest.model.PrimitiveResponse;

public class Estimator extends Primitive<EstimatorPUB> {
	private static final ObjectMapper om = new ObjectMapper()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);

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

		EstimatorRequest req = new EstimatorRequest(getBackend().getName(),pubs);
		
		try {
			String pretty = om.writerWithDefaultPrettyPrinter().writeValueAsString(req);
			System.out.println(pretty);
			
			Job res = service.createJob(req);
			return res;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
//		System.out.println(res.getId());
		return null;
	}
	
	public static class EstimatorRequest extends PrimitiveRequest {

		public EstimatorRequest(String backend,List<EstimatorPUB> pubs) {
			super(backend,pubs,"estimator");
		}
		
	}
	

	public static class EstimatorResponse extends PrimitiveResponse {
//	    private String id;
//	    private String backend;
//	    private JobState state;
//	    private JobStatus status;
//	    private Map<String, Object> params;
//	    private Program program;
//	    private String created;
//	    private String runtime;
//	    private Integer cost;
//	    private List<String> tags;
		
	}

}
