package com.gnf.qrest;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnf.qrest.builders.BackendsRequest;
import com.gnf.qrest.builders.EstimatorPUB;
import com.gnf.qrest.builders.SamplerPUB;
import com.gnf.qrest.model.Backend;
import com.gnf.qrest.model2.Complex;
import com.gnf.qrest.model2.PrimitiveResults;
import com.gnf.qrest.model2.PrimitiveResults.Result;
import com.gnf.qrest.model2.PrimitiveResults.Result.EstimatorData;
import com.gnf.qrest.model2.PrimitiveResults.Result.SamplerData;
import com.gnf.qrest.model2.PrimitiveResults.Result.SamplerData.SamplerRegisters;
import com.gnf.qrest.model2.Workload;
import com.gnf.qrest.model2.Workloads;
import com.gnf.qrest.qiskit.Estimator;
import com.gnf.qrest.qiskit.Job;
import com.gnf.qrest.qiskit.Jobs;
import com.gnf.qrest.qiskit.Pauli;
import com.gnf.qrest.qiskit.Sampler;
import com.gnf.qrest.qiskit.Session;
import com.gnf.qrest.qiskit.SparsePauliOp;


public class QTest {
	
	private static final String BACKEND = "ibm_torino";
//	private static final String BACKEND = "ibm_fez";
//	private static final String BACKEND = "ibm_marrakesh";
	private static final ObjectMapper om = new ObjectMapper()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	private static final String API = "https://quantum.cloud.ibm.com/api/v1";
	private static QiskitRuntimeService service = QiskitRuntimeService.getInstance();

	public static void main(String[] args) {
		QTest qt = new QTest();

//		qt.testWorkloads();
//		qt.testBackends();
//		qt.testJobs();
//		qt.testSession();
//		qt.testSessionJobs();
//		qt.testEstimator();
//		qt.testSampler();
//		qt.testSamplerParams();
//		qt.testSamplerParamsComplete();
//		qt.testResultsSampler();
//		qt.testResultsEstimator();
		qt.testEstimatorParamsComplete();
//		qt.testStatus();
//		qt.testSamplerComplete();
//		qt.testEstimatorComplete();
//		qt.testPauli();
	}
	
	private void testPauli() {
		try {
//			Pauli p = new Pauli("XI", new Complex(0.5,0d));
			Pauli p = new Pauli("XI", 2.0);
			
			String pretty = om.writerWithDefaultPrettyPrinter().writeValueAsString(p);
			System.out.println(pretty);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	private void testStatus() {
		System.out.println("Job");
		Job job = service.job("d52lpg9smlfc739e2nh0");
		job.waitForFinalState();
		System.out.println(String.format("%s: %s %s [%s]", job.getId(),job.getCreated(),job.getBackend(),job.getStatus()));
	}
	
	private void testSession() {
		System.out.println("Session Dump");
		Session session = service.session("5cd95f31-764e-4865-a25f-8ecc34ee39f8");
		System.out.println(String.format("%s: %s %s %s", session.getId(),session.getStartedAt(),session.getBackendName(),session.getMode()));
	}
	
	private void testSessionJobs() {
		System.out.println("Session Jobs Dump");
		Jobs jobs = service.sessionJobs("5cd95f31-764e-4865-a25f-8ecc34ee39f8");
		jobsDump(jobs);
	}

	private void testWorkloads() {
		Workloads workloads = service.workloads();
		
		workloadsDump(workloads);
	}

	private void testResultsSampler() {
		// Sampler
		System.out.println("Sampler");
		PrimitiveResults results = service.jobResults("d53ksdnp3tbc73amj1eg");
		if (results!=null) {
			Result result = results.getResults().get(0);
			SamplerData data = (SamplerData) result.getData();
			Map<String, SamplerRegisters> registers = data.getRegisters();
			for (String register : registers.keySet()) {
				List<List<String>> samples = registers.get(register).getSamples();
				for (int i=0;i<samples.size();i++) {
					List<String> ss = samples.get(i);
					System.out.println("\n\n\t"+register+"["+i+"]");
					for (String s: ss) {
						System.out.println("\t"+s);
					}
					System.out.println("\n");
					Map<String, Long> counts = ss.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
					for (String key: counts.keySet()) {
						Long count = counts.get(key);
						System.out.println(key+": "+count);
					}
				}
			}
		}
	}		

	public void testResultsEstimator() {
		System.out.println("Estimator");
		PrimitiveResults results2 = service.jobResults("d53lee1smlfc739f0220");
		if (results2!=null) {
			Result result2 = results2.getResults().get(0);
			EstimatorData data = (EstimatorData) result2.getData();
//			for (Double evs : data.getEvs()) {
//				System.out.println(evs);
//			};
			System.out.println(data.getEvs());
		}

//		// Estimator (Multiple)
////		System.out.println("Estimator (Multiple)");
////		service.resultsJob("d4vme1ng0u6s73dausi0");

	}
	
	private void testEstimator() {
		Backend backend = service.backend(BACKEND);
		Estimator estimator = new Estimator(backend);
		String qasm = "OPENQASM 3.0;include \"stdgates.inc\";rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;";
//		List<String> observables = List.of("XX");
//		SparsePauliOp observables = SparsePauliOp.fromList(List.of(new Pauli("XZ", new Complex(0.5,0.0)),new Pauli("ZX", new Complex(0.5,0.0))));
//		SparsePauliOp observables = SparsePauliOp.fromList(List.of(new Pauli("XZ", new Complex(1.0,0.0)),new Pauli("ZX", new Complex(2.0,0.0))));
//		List<Pauli> observables = List.of(new Pauli("XZ", new Complex(1.0,0.0)),new Pauli("ZX", new Complex(2.0,0.0)));
//		List<Pauli> observables = List.of(new Pauli("XZ", 0.5),new Pauli("ZX", 2.0));
		SparsePauliOp observables = SparsePauliOp.fromSparseList(List.of(new Pauli("XZ", new int[] {0,1},1),new Pauli("ZX", new int[] {0,1},2)),2);
		EstimatorPUB pub = EstimatorPUB.builder().
				circuit(qasm).
				observables(observables.getPaulis()).build();
		Job job = estimator.run(pub);
		if (job!=null) {
			job.cancel();
		}
		
	}

	private void testEstimatorComplete() {
		Backend backend = service.backend(BACKEND);
		Estimator estimator = new Estimator(backend);
		String qasm = "OPENQASM 3.0;include \"stdgates.inc\";rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;";
//		List<String> observables = List.of("XX");
//		List<Pauli> observables = List.of(new Pauli("XX"));
//		SparsePauliOp observables = SparsePauliOp.fromList(List.of(new Pauli("XX")));
//		List<Pauli> observables = List.of(new Pauli("XX",new Complex(2.0,0.0)));
//		List<Pauli> observables = List.of(new Pauli("XZ", new Complex(1.0,0.0)),new Pauli("ZX", new Complex(2.0,0.0)));
//		List<Pauli> observables = List.of(new Pauli("XZ", 0.5),new Pauli("ZX", 2.0));
		SparsePauliOp observables = SparsePauliOp.fromSparseList(List.of(new Pauli("XZ", new int[] {0,1},1),new Pauli("ZX", new int[] {0,1},2)),2);
		EstimatorPUB pub = EstimatorPUB.builder().
				circuit(qasm).
				observables(observables.getPaulis()).build();
		Job job = estimator.run(pub);
		
		String state = job.waitForFinalState();
		
		if (!List.of("Cancelled","Failed").contains(state)) {
			PrimitiveResults results = job.results();
			Result result = results.getResults().get(0);
			EstimatorData data = (EstimatorData)result.getData();
//			Double evs = data.getEvs().get(0);
//			System.out.println(evs);
		} else if (state.equals("Failed")) {
			System.out.println("Failed: "+job.getState().getReason());
		}
	}

	private void testEstimatorParamsComplete() {
		Backend backend = service.backend(BACKEND);
		Estimator estimator = new Estimator(backend);
		String qasm = "OPENQASM 3.0;include \'stdgates.inc\';input float[64] theta;bit[2] c;rz(pi/2) $0;sx $0;rz(pi) $0;rz(-pi/2) $1;rz(pi + theta) $1;sx $1;rz(5*pi/2) $1;cz $1, $0;sx $0;rz(pi/2) $0;barrier $1, $0;c[0] = measure $1;c[1] = measure $0;";
		List<List<Double>> parms = List.of(List.of(3.14),List.of(1.57));
		SparsePauliOp observables = SparsePauliOp.fromSparseList(List.of(new Pauli("XZ", new int[] {0,1},1),new Pauli("ZX", new int[] {0,1},2)),2);
		EstimatorPUB pub = EstimatorPUB.builder().
				circuit(qasm).
				parameters(parms).
				observables(observables.getPaulis()).build();
		Job job = estimator.run(pub);
		
		String state = job.waitForFinalState();
		if (!List.of("Cancelled","Failed").contains(state)) {
			PrimitiveResults results = job.results();
			Result result = results.getResults().get(0);
			EstimatorData data = (EstimatorData)result.getData();
			Double evs = data.getEvs();
			System.out.println(evs);
		} else if (state.equals("Failed")) {
			Job job2 = service.job(job.getId());
			System.out.println("Failed: "+job2.getState().getReason());
		}
	}

	private void testSampler() {
		Backend backend = service.backend(BACKEND);
		Sampler sampler = new Sampler(backend);
		String qasm = "OPENQASM 3.0;include \"stdgates.inc\";bit[2] c;rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;c[0] = measure $0;c[1] = measure $1;";
		SamplerPUB pub = SamplerPUB.builder().
				circuit(qasm).
				shots(16).build();
		Job job = sampler.run(pub);
		
		job.status();
		
//		if (job!=null) {
//			job.cancel();
//		}
		
	}

	private void testSamplerParams() {
		Backend backend = service.backend(BACKEND);
		Sampler sampler = new Sampler(backend);
		String qasm = "OPENQASM 3.0;include \'stdgates.inc\';input float[64] theta;bit[2] c;rz(pi/2) $12;sx $12;rz(pi) $12;rz(-pi/2) $18;rz(pi + theta) $18;sx $18;rz(5*pi/2) $18;cz $18, $12;sx $12;rz(pi/2) $12;barrier $18, $12;c[0] = measure $18;c[1] = measure $12;";
		List<List<Double>> parms = List.of(List.of(3.14));
		SamplerPUB pub = SamplerPUB.builder().
			circuit(qasm).
			parameters(parms).
			shots(16).build();
		Job job = sampler.run(pub);
			
		job.status();
	}
	
	private void testSamplerComplete() {
		Backend backend = service.backend(BACKEND);
		Sampler sampler = new Sampler(backend);
		String qasm = "OPENQASM 3.0;include \"stdgates.inc\";bit[2] c;rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;c[0] = measure $0;c[1] = measure $1;";
		SamplerPUB pub = SamplerPUB.builder().
				circuit(qasm).
				shots(16).build();
		Job job = sampler.run(pub);
		
		String state = job.waitForFinalState();
		
		if (!List.of("Cancelled","Failed").contains(state)) {
			Result results = job.results().getResults().get(0);
			SamplerData data = (SamplerData) results.getData();
			Map<String, SamplerRegisters> registers = data.getRegisters();
			for (String register : registers.keySet()) {
				List<List<String>> samples = registers.get(register).getSamples();
				for (int i=0;i<samples.size();i++) {
					List<String> ss = samples.get(i);
					System.out.println("\n\n\t"+register+"["+i+"]");
					for (String s: ss) {
						System.out.println("\t"+s);
					}
					System.out.println("\n");
					Map<String, Long> counts = ss.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
					for (String key: counts.keySet()) {
						Long count = counts.get(key);
						System.out.println(key+": "+count);
					}
				}
			}
		}		
	}

	private void testSamplerParamsComplete() {
		Backend backend = service.backend(BACKEND);
		Sampler sampler = new Sampler(backend);
		String qasm = "OPENQASM 3.0;include \'stdgates.inc\';input float[64] theta;bit[2] c;rz(pi/2) $12;sx $12;rz(pi) $12;rz(-pi/2) $18;rz(pi + theta) $18;sx $18;rz(5*pi/2) $18;cz $18, $12;sx $12;rz(pi/2) $12;barrier $18, $12;c[0] = measure $18;c[1] = measure $12;";
		List<List<Double>> parms = List.of(List.of(3.14),List.of(1.57),List.of(0.0));
		SamplerPUB pub = SamplerPUB.builder().
				circuit(qasm).
				parameters(parms).
				shots(16).build();
		Job job = sampler.run(pub);
		
		String state = job.waitForFinalState();
		
		if (!List.of("Cancelled","Failed").contains(state)) {
			Result results = job.results().getResults().get(0);
			SamplerData data = (SamplerData) results.getData();
			Map<String, SamplerRegisters> registers = data.getRegisters();
			for (String register : registers.keySet()) {
				List<List<String>> samples = registers.get(register).getSamples();
				for (int i=0;i<samples.size();i++) {
					List<String> ss = samples.get(i);
					System.out.println("\n\n\t"+register+"["+i+"]");
					for (String s: ss) {
						System.out.println("\t"+s);
					}
					System.out.println("\n");
					Map<String, Long> counts = ss.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
					for (String key: counts.keySet()) {
						Long count = counts.get(key);
						System.out.println(key+": "+count);
					}
				}
			}
		}		
	}

	public void testJobs() {
		System.out.println("ALL Jobs Dump");
		Jobs jobs = service.jobs();
		jobsDump(jobs);
	}

	public void testBackends() {
		List<Backend> bb = service.backends(BackendsRequest.builder().build());
//		List<Backend> dd = service.backends(BackendsRequest.builder().minNumQubits(156).build());
//		List<Backend> dd = service.backends(BackendsRequest.builder().name("ibm_fez").build());
		
		for (Backend b: bb) {
			System.out.println(b.getName()+" ("+b.getQubits()+"): "+b.getQueueLength());// +" ["+b.getStatus().getName()+"]");
		}
		
	}
	
	private void jobsDump(Jobs jobs) {
		for (Job job: jobs.getJobs()) {
			System.out.println(job);
		}
	}

	private void workloadsDump(Workloads workloads) {
		for (Workload workload : workloads.getWorkloads()) {
			System.out.println(workload);
		}
	}
	
}