package com.gnf.qrest.qiskit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnf.qrest.builders.PUB;
import com.gnf.qrest.model.Backend;

public abstract class Primitive<P extends PUB>{
	private static final ObjectMapper om = new ObjectMapper()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);

	private Backend backend;
	
	public Primitive(Backend backend) {
		this.backend = backend;
	}
	public abstract Job run(P pub);
//	public Job run(PUB pub) {
//		QiskitRuntimeService service = QiskitRuntimeService.getInstance();
//
//		PrimitiveRequest req = new PrimitiveRequest(backend.getName(),pub);
//		
//		try {
//			String pretty = om.writerWithDefaultPrettyPrinter().writeValueAsString(req);
//			System.out.println(pretty);
//			
//			Job res = service.createJob(req);
//			return res;
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
////		System.out.println(res.getId());
//		return null;
//	}

	public Backend getBackend() {
		return backend;
	}

	public void setBackend(Backend backend) {
		this.backend = backend;
	}
}
