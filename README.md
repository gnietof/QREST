# QREST

Java implementation of a REST client for IBM Quantum.

## Examples
We will be using a static instance of the ObjectMapper. We will also create a instance of the `QiskitRuntimeService` class which includes all the methods for interfacing with IBM Quantum backends.
	
```java
	private static final ObjectMapper om = new ObjectMapper()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	private static final String API = "https://quantum.cloud.ibm.com/api/v1";

  	private static QiskitRuntimeService service = QiskitRuntimeService.getInstance();
	private static TranspilationService transpilation = TranspilationService.getInstance();

```
### Job list
- Retrieve the list of jobs which have been submited and also get the QPU usage by year/month.
```java
	Jobs jobs = service.jobs();
	for (Job job: jobs.getJobs()) {
		System.out.println(job);
	}

	Map<YearMonth,Integer> totals = jobs.getJobs().stream().
		collect(Collectors.groupingBy(job -> YearMonth.from(Instant.parse(job.getCreated()).atZone(ZoneOffset.UTC).toLocalDate()),
			Collectors.summingInt(job -> job.getUsage().getQuantumSeconds())));
	
	for (YearMonth ym : totals.keySet()) {
		System.out.println(String.format("%s: %d",ym,totals.get(ym)));
	}
```
### Job submission
These are the circuits we will be using for the Sampler and the Estimator using QASM2 and QASM3 syntaxes (with or without a parameter).  
<img width="241" height="174" alt="image" src="https://github.com/user-attachments/assets/9bb9f823-fd42-42ec-9455-defe77054396" />
<img width="241" height="174" alt="image" src="https://github.com/user-attachments/assets/6bd1d688-17a5-414b-8cbf-37211947e433" />

The circuit in QASM (no parameters):
```java		
	String qasm = "OPENQASM 3.0;include \"stdgates.inc\";bit[2] c;rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;c[0] = measure $0;c[1] = measure $1;";
	String qasm = "OPENQASM 2.0;include \"qelib1.inc\";qreg q[133];creg c[2];rz(pi/2) q[0];sx q[0];rz(pi/2) q[0];rz(pi/2) q[1];sx q[1];rz(pi/2) q[1];cz q[0],q[1];rz(pi/2) q[1];sx q[1];rz(pi/2) q[1];measure q[0] -> c[0];measure q[1] -> c[1];";
```

The circuit in QASM (with parameters; QASM2 does not support them).
```java		
	String qasm = "OPENQASM 3.0;include "stdgates.inc";input float[64] theta;bit[2] c;rz(-pi/2) $65;rz(pi + theta) $65;sx $65;rz(5*pi/2) $65;rz(pi/2) $66;sx $66;rz(pi) $66;cz $65, $66;sx $66;rz(pi/2) $66;barrier $65, $66;c[0] = measure $65;c[1] = measure $66;"
```

⚠️**Note**: Although QASM2 and QASM3 might be used, there is a problem with the way QASM3 is generated from a Qiskit QuantumCircuit and the layouts used for the observables related with the number of qubits.
On the other hand, using parameters is not supported in QASM2.

#### Samplers
- Run a Sampler primitive with no parameters.
```java
	Backend backend = service.backend(BACKEND);

	Sampler sampler = new Sampler(backend);
	String qasm = "OPENQASM 3.0;include \"stdgates.inc\";bit[2] c;rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;c[0] = measure $0;c[1] = measure $1;";
	SamplerPUB pub = new SamplerPUB.Builder().
			circuit(qasm).
			shots(16).
			build();

	Job job = sampler.run(pub);
	service.job(job.getId()).getStatus();
```
- Run a Sampler primitive with one parameter.
```java
	Backend backend = service.backend(BACKEND);

	Sampler sampler = new Sampler(backend);
	String qasm = "OPENQASM 3.0;include \'stdgates.inc\';input float[64] theta;bit[2] c;rz(pi/2) $12;sx $12;rz(pi) $12;rz(-pi/2) $18;rz(pi + theta) $18;sx $18;rz(5*pi/2) $18;cz $18, $12;sx $12;rz(pi/2) $12;barrier $18, $12;c[0] = measure $18;c[1] = measure $12;";
	List<List<Double>> parms = List.of(List.of(3.14),List.of(1.57));
	SamplerPUB pub = new SamplerPUB.Builder().
		circuit(qasm).
		parameters(parms).
		shots(16).build();

	Job job = sampler.run(pub);
	service.job(job.getId()).getStatus();
```

#### Estimators
- Run an Estimator primitive with one observable but no parameters or precission.
```java
	Backend backend = service.backend(BACKEND);

	Estimator estimator = new Estimator(backend);
	String qasm = "OPENQASM 3.0;include \"stdgates.inc\";rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;";
	SparsePauliOp observables = SparsePauliOp.fromSparseList(new Paulis(new Pauli("XZ", new int[] {0,1},1),new Pauli("ZX", new int[] {0,1},2)),2);
	EstimatorPUB pub = new EstimatorPUB.Builder().
		circuit(qasm).
		observable(observables).
		build();

	Job job = estimator.run(pub);
	service.tags(job.getId(), new Tags(List.of("Estimator")));
```
- Run an Estimator primitive with two observables but no parameters or precission.
```java
		Backend backend = service.backend(BACKEND);

		Estimator estimator = new Estimator(backend);
		String qasm = "OPENQASM 3.0;include \"stdgates.inc\";rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;";
		SparsePauliOp observables1 = SparsePauliOp.fromSparseList(new Paulis(new Pauli("YZ", new int[] {0,1},2),new Pauli("ZY", new int[] {0,1},1)),2);
		SparsePauliOp observables2 = SparsePauliOp.fromSparseList(new Paulis(new Pauli("XZ", new int[] {0,1},1),new Pauli("ZX", new int[] {0,1},2)),2);
		EstimatorPUB pub1 = new EstimatorPUB.Builder().
				circuit(qasm).
				observables(List.of(observables1.getPaulis())).build();
		EstimatorPUB pub2 = new EstimatorPUB.Builder().
				circuit(qasm).
				observables(List.of(observables2.getPaulis())).build();

		Job job = estimator.run(List.of(pub1,pub2));
		if (job!=null) {
			service.tags(job.getId(), new Tags("Broad","Estimator"));
		}
```
### Job status
-  Check the status of a job and wait for completion. This code is already provided in a method in `QiskitRuntimeService`.
```java
	public Job waitForFinalState(String id) {
		Job job = null;
		
		while (true) {
			job = job(id,false);
			String status = job.getStatus();
			System.out.println(String.format("%s: %s",id,status));
			boolean isFinal = !(status.equals("Queued") || status.equals("Running"));
			if (isFinal) {
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
```
### Job results
#### Samplers
- Retrieve Sampler results.
```java
		PrimitiveResults results = service.jobResults(job.getId());

		for (Result result: results.getResults()) {
			SamplerData data = (SamplerData) result.getData();
			Map<String, SamplerRegisters> registers = data.getRegisters();
			for (String key1 : registers.keySet()) {
				System.out.println(String.format("\n\n** %s",key1));
				SamplerRegisters register = registers.get(key1);
				for (int i=0;i<register.size();i++) {
					System.out.println(String.format("\t%d",i));
					BitString ss = register.getBitString(i);
					System.out.println(ss);
					Map<String, Long> counts1 = register.getCounts(i);
					for (String key: counts1.keySet()) {
						Long count = counts1.get(key);
						System.out.println(key+": "+count);
					}
					Map<Integer, Long> counts2 = register.getIntCounts(i);
					for (Integer key: counts2.keySet()) {
						Long count = counts2.get(key);
						System.out.println(key+": "+count);
					}
				}
			}
		}
```
- It is also possible to retrieve the count of each of the results using either the strings or the decimal values as keys.
```java
		PrimitiveResults results = service.jobResults(job.getId());

		for (Result result: results.getResults()) {
			SamplerData data = (SamplerData) result.getData();
			Map<String, SamplerRegisters> registers = data.getRegisters();
			for (String key1 : registers.keySet()) {
				System.out.println(String.format("\n\n** %s",key1));
				SamplerRegisters register = registers.get(key1);
				for (int i=0;i<register.size();i++) {
					Map<String, Long> counts1 = register.getCounts(i);
					for (String key: counts1.keySet()) {
						Long count = counts1.get(key);
						System.out.println(key+": "+count);
					}
					Map<Integer, Long> counts2 = register.getIntCounts(i);
					for (Integer key: counts2.keySet()) {
						Long count = counts2.get(key);
						System.out.println(key+": "+count);
					}
				}
			}
		}
```
- The bitstreams can also be retrieved as a single list including all the samples.
```java
		PrimitiveResults results = service.jobResults(job.getId());

		for (Result result: results.getResults()) {
			SamplerData data = (SamplerData) result.getData();
			Map<String, SamplerRegisters> registers = data.getRegisters();
			for (String key1 : registers.keySet()) {
				System.out.println(String.format("\n\n** %s",key1));
				SamplerRegisters register = registers.get(key1);
				BitString ss = register.getBitString();
				System.out.println(ss);
				Map<String, Long> counts1 = register.getCounts();
				for (String key: counts1.keySet()) {
					Long count = counts1.get(key);
					System.out.println(key+": "+count);
				}
				System.out.println("\n");
				Map<Integer, Long> counts2 = register.getIntCounts();
				for (Integer key: counts2.keySet()) {
					Long count = counts2.get(key);
					System.out.println(key+": "+count);
				}
				
			}
		}
```
#### Estimators
- Retrieve Sampler results.
```java
		PrimitiveResults results = service.jobResults(job.getId());

		for (Result result: results.getResults()) {
			SamplerData data = (SamplerData) result.getData();
			Map<String, SamplerRegisters> registers = data.getRegisters();
			for (String key1 : registers.keySet()) {
				System.out.println(String.format("\n\n** %s",key1));
				SamplerRegisters register = registers.get(key1);
				for (int i=0;i<register.size();i++) {
					System.out.println(String.format("\t%d",i));
					BitString ss = register.getBitString(i);
					System.out.println(ss);
					Map<String, Long> counts1 = register.getCounts(i);
					for (String key: counts1.keySet()) {
						Long count = counts1.get(key);
						System.out.println(key+": "+count);
					}
					Map<Integer, Long> counts2 = register.getIntCounts(i);
					for (Integer key: counts2.keySet()) {
						Long count = counts2.get(key);
						System.out.println(key+": "+count);
					}
				}
				System.out.println("\n\tAll");
				Map<String, Long> counts1 = register.getCounts();
				for (String key: counts1.keySet()) {
					Long count = counts1.get(key);
					System.out.println(key+": "+count);
				}
				System.out.println("\n");
				Map<Integer, Long> counts2 = register.getIntCounts();
				for (Integer key: counts2.keySet()) {
					Long count = counts2.get(key);
					System.out.println(key+": "+count);
				}
				
			}
		}
```
- Retrieve Estimator results
```java
		JobResults jr = qt.getJobResults(id);
		List<Result> results = jr.getResults();
		Result result = results.get(0);
		Data data = result.getData();
		Double evs = data.getEvs().get(0);
```		

