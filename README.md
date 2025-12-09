# QREST

Java implementation of a REST client for IBM Quantum.

## Examples
We will be using a static instance of the ObjectMapper. We will also create a instance of the QTool class which includes all the methods for interfacing with IBM Quantum backends.
	
```java
	private static final ObjectMapper om = new ObjectMapper()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	private static final String API = "https://quantum.cloud.ibm.com/api/v1";

  QTool qt = new QTool();
```
### Job list
- Retriee the list of jobs which have been submited.
```java
		QTool qt = new QTool();

		JobsResponse jjrr = qt.listJobs();
		for (JobResponse jr: jjrr.getJobs()) {
			String backend = jr.getBackend();
			String created = jr.getCreated();
			JobStatus status = jr.getStatus();
			System.out.println(String.format("%s: %s - %s",created,backend,status.toString()));
		}
```
### Job submission
This is the circuit we will be using for the Sampler and the Estimator using QASM2 and QASM3 syntaxes.  
<img width="241" height="174" alt="image" src="https://github.com/user-attachments/assets/9bb9f823-fd42-42ec-9455-defe77054396" />

```java		
		String qasm = "OPENQASM 3.0;include \"stdgates.inc\";bit[2] c;rz(pi/2) $0;sx $0;rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;c[0] = measure $0;c[1] = measure $1;";
		String qasm = "OPENQASM 2.0;include \"qelib1.inc\";qreg q[133];creg c[2];rz(pi/2) q[0];sx q[0];rz(pi/2) q[0];rz(pi/2) q[1];sx q[1];rz(pi/2) q[1];cz q[0],q[1];rz(pi/2) q[1];sx q[1];rz(pi/2) q[1];measure q[0] -> c[0];measure q[1] -> c[1];";
``` 		
- Run a Sampler with no parameters or shots.
```java
		JobRunResponse res = qt.runJob("ibm_torino",qasm);
		String id = res.getId();
```

- Run a Estimator with one observable but no parameters or precission.
```java
		JobRunResponse res = qt.runJob("ibm_torino",qasm,Arrays.asList("XX"));
		String id = res.getId();
```
### Job status
-  Check the status of a job and wait for completion
```java
		JobStateStatus status = null;
		do {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			JobDetails jd = qt.getJobDetails(id);
			status = jd.getState().getStatus();
			System.out.println(id+": "+status);
		} while (EnumSet.of(JobStateStatus.Queued, JobStateStatus.Running).contains(status));
```
### Job results
- Retrieve Sampler results
```java
		JobResults jr = qt.getJobResults(id);
		List<Result> results = jr.getResults();
		Result result = results.get(0);
		Data data = result.getData();
		C c= data.getC();
        List<String> samples = c.getSamples();
```

- Retrieve Estimator results
```java
		JobResults jr = qt.getJobResults(id);
		List<Result> results = jr.getResults();
		Result result = results.get(0);
		Data data = result.getData();
		Double evs = data.getEvs().get(0);
```		

