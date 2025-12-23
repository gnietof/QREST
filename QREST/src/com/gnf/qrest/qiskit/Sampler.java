package com.gnf.qrest.qiskit;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnf.qrest.QiskitRuntimeService;
import com.gnf.qrest.builders.SamplerPUB;
import com.gnf.qrest.model.Backend;
import com.gnf.qrest.model.PrimitiveRequest;

public class Sampler extends Primitive<SamplerPUB> {
	private static final ObjectMapper om = new ObjectMapper()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);

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

		SamplerRequest req = new SamplerRequest(getBackend().getName(),pubs);
		
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
	
	public static class SamplerRequest extends PrimitiveRequest {

		public SamplerRequest(String backend,List<SamplerPUB> pubs) {
			super(backend,pubs,"sampler");
		}
		
	}
	

	public static class SamplerResponse {
	}

}
