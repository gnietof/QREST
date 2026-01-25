package com.gnf.qrest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gnf.qrest.model.Backend;
import com.gnf.qrest.model.BackendProps;
import com.gnf.qrest.model.BackendProps.Gate;
import com.gnf.qrest.model.BackendStatus;
import com.gnf.qrest.model.BackendsRequest;
import com.gnf.qrest.model.BitString;
import com.gnf.qrest.model.EstimatorPUB;
import com.gnf.qrest.model.Paulis;
import com.gnf.qrest.model.PrimitiveResults;
import com.gnf.qrest.model.PrimitiveResults.Result;
import com.gnf.qrest.model.PrimitiveResults.Result.EstimatorData;
import com.gnf.qrest.model.PrimitiveResults.Result.SamplerData;
import com.gnf.qrest.model.PrimitiveResults.Result.SamplerData.SamplerRegisters;
import com.gnf.qrest.model.QResponse;
import com.gnf.qrest.model.QResponse.QError;
import com.gnf.qrest.model.SamplerPUB;
import com.gnf.qrest.model.Tags;
import com.gnf.qrest.model.Workload;
import com.gnf.qrest.model.Workloads;
import com.gnf.qrest.qiskit.Estimator;
import com.gnf.qrest.qiskit.Job;
import com.gnf.qrest.qiskit.Jobs;
import com.gnf.qrest.qiskit.Pauli;
import com.gnf.qrest.qiskit.Sampler;
import com.gnf.qrest.qiskit.Session;
import com.gnf.qrest.qiskit.SparsePauliOp;
import com.gnf.qrest.simulator.EstimatorResponse;
import com.gnf.qrest.simulator.SamplerResponse;
import com.gnf.qrest.transpilation.LayoutResponse;
import com.gnf.qrest.transpilation.TranspilationService;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class for running tests.
 */
public class QTest {

  private static final String BACKEND = "ibm_torino";
  // private static final String BACKEND = "ibm_fez";
  // private static final String BACKEND = "ibm_marrakesh";
  private static final ObjectMapper om = JsonMapper.builder()
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
      .serializationInclusion(JsonInclude.Include.NON_NULL).build();
  private static QiskitRuntimeService service = QiskitRuntimeService.getInstance();
  private static TranspilationService transpilation = TranspilationService.getInstance();

  /**
   * Main method for testing code.
   * 
   * @param args The args provided to the main method.
   */
  public static void main(String[] args) {
    QTest qt = new QTest();

    qt.test("d56hin8nsj9s73b3q68g");
    qt.test("d5a7e79smlfc739l8tdg");
    // qt.dumpJobs();
    // qt.testSamplerSimulate();
    // qt.testEstimatorSimulate();
    // qt.testTranspileCircuit();
    // qt.testLayoutCircuit();
    // qt.testDrawCircuit();
    // qt.testLayoutEstimatorCircuit();
    // qt.testCancel();
    // qt.testWorkloads();
    // qt.testBackends();
    // qt.testJobs();
    // qt.testSession();
    // qt.testSessionJobs();
    // qt.testEstimator();
    // qt.testSampler();
    // qt.testSamplerParams();
    // qt.testSamplerX2();
    // qt.testSamplerParamsX2();
    // qt.testSamplerParamsComplete();
    // qt.testDetails();
    // qt.testResultsSampler();
    // qt.testResultsEstimator();
    // qt.testBroadEstimator();
    // qt.testBroadParamsEstimator();
    // qt.testEstimatorParamsComplete();
    // qt.testStatus();
    // qt.testSamplerComplete();
    // qt.testEstimatorComplete();
    // qt.testPauli();
    // qt.testTags();

  }

  private void testLayoutCircuit() {
    String circuit = "qc = QuantumCircuit(3)\nqc.h(0)\nqc.cx(0,1)\nqc.cx(0,2)";
    SparsePauliOp sparse1 = SparsePauliOp.fromSparseList(new Paulis(
        new Pauli("YZX", new int[] { 0, 1, 2 }, 2), new Pauli("XZY", new int[] { 0, 1, 2 }, 1)), 3);
    SparsePauliOp sparse2 = SparsePauliOp.fromSparseList(new Paulis(
        new Pauli("XYZ", new int[] { 0, 1, 2 }, 1), new Pauli("ZXY", new int[] { 0, 1, 2 }, 2)), 3);
    List<Paulis> observables = List.of(sparse1.getPaulis(), sparse2.getPaulis());
    LayoutResponse layout = transpilation.layout(BACKEND, circuit, observables, 1);

    for (Paulis pp : layout.getObservables()) {
      for (Pauli p : pp.asList()) {
        System.out.println(p);
      }
    }

  }

  private void testLayoutEstimatorCircuit() {
    Backend backend = service.backend(BACKEND);
    Estimator estimator = new Estimator(backend);

    String circuit = "qc = QuantumCircuit(3)\nqc.h(0)\nqc.cx(0,1)\nqc.cx(0,2)";
    SparsePauliOp sparse1 = SparsePauliOp.fromSparseList(new Paulis(
        new Pauli("YZX", new int[] { 0, 1, 2 }, 2), new Pauli("XZY", new int[] { 0, 1, 2 }, 1)), 3);
    SparsePauliOp sparse2 = SparsePauliOp.fromSparseList(new Paulis(
        new Pauli("XYZ", new int[] { 0, 1, 2 }, 1), new Pauli("ZXY", new int[] { 0, 1, 2 }, 2)), 3);
    List<Paulis> observables = List.of(sparse1.getPaulis(), sparse2.getPaulis());
    LayoutResponse layout = transpilation.layout(BACKEND, circuit, observables, 1);

    String qasm = layout.getQASM();

    EstimatorPUB pub1 = new EstimatorPUB.Builder().circuit(qasm)
        .observables(List.of(layout.getObservables().get(0))).build();
    EstimatorPUB pub2 = new EstimatorPUB.Builder().circuit(qasm)
        .observables(List.of(layout.getObservables().get(1))).build();
    Job job = estimator.run(List.of(pub1, pub2));

    if (job != null) {
      service.tags(job.getId(), new Tags(List.of("Broad", "Estimator")));
    }

    if (job != null) {
      job.cancel();
    }

    // job = service.waitForFinalState(job.getId());
    //
    // if (job.isDone()) {
    // PrimitiveResults results = service.jobResults(job.getId());
    // dumpEvs(results);
    // } else if (state.equals("Failed")) {
    // System.out.println("Failed: " + job.getState().getReason());
    // }

  }

  private void testTranspileCircuit() {

    String circuit = null;
    try {
      FileInputStream fis = new FileInputStream("/home/genaro/divN_365.py");
      circuit = new String(fis.readAllBytes());
      fis.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (circuit != null) {
      String qasm = transpilation.transpile(BACKEND, circuit, 3);
      System.out.println("QASM3: " + qasm);

      Backend backend = service.backend(BACKEND);
      Sampler sampler = new Sampler(backend);
      List<Float> probs = List.of(0.5f, 0.75f, 0.8f, 0.9f);
      List<SamplerPUB> pubs = new ArrayList<SamplerPUB>();
      for (Float prob : probs) {
        int shots = calculateShots(prob, 365);
        SamplerPUB pub = new SamplerPUB.Builder().circuit(qasm).shots(shots).build();
        pubs.add(pub);
      }
      Job job = sampler.run(pubs);

      service.tags(job.getId(), new Tags("divN", "divN365", "divN365x4"));

      job = service.waitForFinalState(job.getId());

      // if (job.isDone()) {
      // Result results = service.jobResults(job.getId()).getResults().get(0);
      // SamplerData data = (SamplerData) results.getData();
      // Map<String, SamplerRegisters> registers = data.getRegisters();
      // for (String register : registers.keySet()) {
      // List<BitString> samples = registers.get(register).getSamples();
      // for (int i = 0; i < samples.size(); i++) {
      // BitString ss = samples.get(i);
      // System.out.println("\n\n\t" + register + "[" + i + "]");
      // for (String s : ss) {
      // System.out.println("\t" + s);
      // }
      // System.out.println("\n");
      // Map<String, Long> counts = ss.stream()
      // .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      // for (String key : counts.keySet()) {
      // Long count = counts.get(key);
      // System.out.println(key + ": " + count);
      // }
      // }
      // }
      // }

      if (job.isDone()) {
        PrimitiveResults results = service.jobResults(job.getId());
        if (results != null) {
          List<QError> errors = results.getErrors();
          if (errors == null) {
            dumpCounts(results);
          } else {
            for (QError error : errors) {
              System.out.println(error.getMessage());
            }
          }
        }
      }

    }

  }

  private int calculateShots(float prob, int options) {
    int n = 0;
    float nProb = 1;

    while (true) {
      n += 1;
      nProb = nProb * (options - (n - 1)) / options;
      float yProb = 1 - nProb;
      if (yProb >= prob) {
        return n;
      }
    }
  }

  private void testDrawCircuit() {
    try (FileOutputStream fos = new FileOutputStream("/home/genaro/cricuit.png")) {
      String circuit = "qc = QuantumCircuit(2)\nqc.h(0)\nqc.cx(0,1)";
      transpilation.draw(circuit, fos);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void testCancel() {
    service.cancelJob("d53llspsmlfc739f08q0");
  }

  private void testTags() {
    Tags tags2 = service.searchTags(null);
    if (tags2 != null) {
      for (String tag : tags2.getTags()) {
        System.out.println(tag);
      }
    } else {
      System.out.println("No tags found!");
    }

  }

  private void testPauli() {
    try {
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
    service.waitForFinalState(job.getId());
    System.out.println(String.format("%s: %s %s [%s]", job.getId(), job.getCreated(),
        job.getBackend(), job.getStatus()));
  }

  private void testSession() {
    System.out.println("Session Dump");
    Session session = service.session("5cd95f31-764e-4865-a25f-8ecc34ee39f8");
    System.out.println(String.format("%s: %s %s %s", session.getId(), session.getStartedAt(),
        session.getBackendName(), session.getMode()));
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

  private void testDetails() {
    service.jobDetails("d54hjs7p3tbc73andsog");
  }

  private void testResultsSampler() {
    // Sampler
    System.out.println("Sampler");
    // PrimitiveResults results = service.jobResults("d564l5np3tbc73aoue60"); // 1x1
    // PrimitiveResults results = service.jobResults("d566ep7p3tbc73ap03l0"); // 2x1
    // PrimitiveResults results = service.jobResults("d566nuhsmlfc739hde30"); // 2x3
    // PrimitiveResults results = service.jobResults("d57dllbht8fs73a2nkt0"); // divN365
    PrimitiveResults results = service.jobResults("d57eft8nsj9s73b4mcbg"); // divN365x3
    if (results != null) {
      List<QError> errors = results.getErrors();
      if (errors == null) {
        dumpCounts(results);
      } else {
        for (QError error : errors) {
          System.out.println(error.getMessage());
        }
      }
    }
  }

  /**
   * Estimator results test.
   */
  public void testResultsEstimator() {
    System.out.println("Estimator");
    // PrimitiveResults results2 = service.jobResults("d53lee1smlfc739f0220"); // Single
    // PrimitiveResults results2 = service.jobResults("d53lqu7p3tbc73amjt00"); // Multiple
    System.out.println("\nEstimator 1x1");
    PrimitiveResults results = service.jobResults("d5439c3ht8fs739vgu0g"); // Broad 2x1
    dumpEvs(results);
    System.out.println("\nEstimator 2x1");
    results = service.jobResults("d54iagjht8fs739vv13g"); // Broad 2x1
    dumpEvs(results);
    System.out.println("\nEstimator 2x2");
    results = service.jobResults("d54hjs7p3tbc73andsog"); // Broad 2x2
    dumpEvs(results);
    System.out.println("\nEstimator 2x2x2");
    results = service.jobResults("d54irvfp3tbc73anf1ng"); // Broad 2x2x2
    dumpEvs(results);

  }

  /**
   * Dumps EVS.
   */
  private void dumpEvs(PrimitiveResults results) {
    List<Result> results2 = results.getResults();
    for (int i = 0; i < results2.size(); i++) {
      System.out.println("\tResults " + i + ":");
      Result result = results2.get(i);
      EstimatorData data = (EstimatorData) result.getData();
      List<List<Double>> eevvss = data.getEvs();
      for (int j = 0; j < eevvss.size(); j++) {
        List<Double> evs = eevvss.get(j);
        for (Double d : evs) {
          System.out.println(d);
        }
      }
    }

  }

  private void test(String id) {
    PrimitiveResults pr = service.jobResults(id);
    List<Result> results = pr.getResults();
    if (results != null) {
      for (int i = 0; i < results.size(); i++) {
        System.out.println("\tResults " + i + ":");
        Result result = results.get(i);
        EstimatorData data = (EstimatorData) result.getData();
        List<List<Double>> evss = data.getEvs();
        for (int j = 0; j < evss.size(); j++) {
          List<Double> evs = evss.get(j);
          for (Double d : evs) {
            System.out.println("Evs: " + d);
          }
        }
        List<List<Double>> stdss = data.getStds();
        for (int j = 0; j < stdss.size(); j++) {
          List<Double> stds = stdss.get(j);
          for (Double d : stds) {
            System.out.println("Stds: " + d);
          }
        }
      }
    }

  }

  /**
   * Dumps counts.
   */
  private void dumpCounts(PrimitiveResults results) {
    for (Result result : results.getResults()) {
      SamplerData data = (SamplerData) result.getData();
      Map<String, SamplerRegisters> registers = data.getRegisters();
      for (String key1 : registers.keySet()) {
        System.out.println(String.format("\n\n** %s", key1));
        SamplerRegisters register = registers.get(key1);
        for (int i = 0; i < register.size(); i++) {
          System.out.println(String.format("\t%d", i));
          BitString ss = register.getBitString(i);
          System.out.println(ss);
          Map<String, Long> counts1 = register.getCounts(i);
          for (String key : counts1.keySet()) {
            Long count = counts1.get(key);
            System.out.println(key + ": " + count);
          }
          Map<Integer, Long> counts2 = register.getIntCounts(i);
          for (Integer key : counts2.keySet()) {
            Long count = counts2.get(key);
            System.out.println(key + ": " + count);
          }
        }
        System.out.println("\n\tAll");
        BitString ss = register.getBitString();
        System.out.println(ss);
        Map<String, Long> counts1 = register.getCounts();
        for (String key : counts1.keySet()) {
          Long count = counts1.get(key);
          System.out.println(key + ": " + count);
        }
        System.out.println("\n");
        Map<Integer, Long> counts2 = register.getIntCounts();
        for (Integer key : counts2.keySet()) {
          Long count = counts2.get(key);
          System.out.println(key + ": " + count);
        }

      }
    }
  }

  /**
   * Estimator test.
   */
  private void testEstimator() {
    Backend backend = service.backend(BACKEND);
    Estimator estimator = new Estimator(backend);
    String qasm = "OPENQASM 3.0;include \"stdgates.inc\";rz(pi/2) $0;sx $0;rz(pi/2) $0;"
        + "rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;";
    SparsePauliOp observables = SparsePauliOp.fromSparseList(
        new Paulis(new Pauli("XZ", new int[] { 0, 1 }, 1), new Pauli("ZX", new int[] { 0, 1 }, 2)),
        2);
    EstimatorPUB pub = new EstimatorPUB.Builder().circuit(qasm).observable(observables).build();
    Job job = estimator.run(pub);

    service.tags(job.getId(), new Tags(List.of("Estimator")));

    if (job != null) {
      job.cancel();
    }

  }

  /**
   * Broadcast Estimator test.
   */
  private void testBroadEstimator() {
    Backend backend = service.backend(BACKEND);
    Estimator estimator = new Estimator(backend);
    String qasm = "OPENQASM 3.0;include \"stdgates.inc\";rz(pi/2) $0;sx $0;rz(pi/2) $0;"
        + "rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;";
    SparsePauliOp observables1 = SparsePauliOp.fromSparseList(
        new Paulis(new Pauli("YZ", new int[] { 0, 1 }, 2), new Pauli("ZY", new int[] { 0, 1 }, 1)),
        2);
    SparsePauliOp observables2 = SparsePauliOp.fromSparseList(
        new Paulis(new Pauli("XZ", new int[] { 0, 1 }, 1), new Pauli("ZX", new int[] { 0, 1 }, 2)),
        2);
    EstimatorPUB pub1 = new EstimatorPUB.Builder().circuit(qasm)
        .observables(List.of(observables1.getPaulis())).build();
    EstimatorPUB pub2 = new EstimatorPUB.Builder().circuit(qasm)
        .observables(List.of(observables2.getPaulis())).build();
    Job job = estimator.run(List.of(pub1, pub2));

    if (job != null) {
      service.tags(job.getId(), new Tags("Broad", "Estimator"));
    }

    if (job != null) {
      job.cancel();
    }

  }

  /**
   * Broadcast Estimator test with parameters.
   */
  private void testBroadParamsEstimator() {
    Backend backend = service.backend(BACKEND);
    Estimator estimator = new Estimator(backend);
    String qasm = "OPENQASM 3.0;include \'stdgates.inc\';input float[64] theta;bit[2] c;"
        + "rz(pi/2) $0;sx $0;rz(pi) $0;rz(-pi/2) $1;rz(pi + theta) $1;sx $1;rz(5*pi/2) $1;"
        + "cz $1, $0;sx $0;rz(pi/2) $0;barrier $1, $0;c[0] = measure $1;c[1] = measure $0;";
    List<List<Double>> parms1 = List.of(List.of(3.14), List.of(1.57));
    List<List<Double>> parms2 = List.of(List.of(1.57), List.of(3.14));
    List<List<Double>> parms3 = List.of(List.of(0.0), List.of(3.14));
    SparsePauliOp observables11 = SparsePauliOp.fromSparseList(
        new Paulis(new Pauli("YZ", new int[] { 0, 1 }, 2), new Pauli("ZY", new int[] { 0, 1 }, 1)),
        2);
    SparsePauliOp observables12 = SparsePauliOp.fromSparseList(
        new Paulis(new Pauli("XY", new int[] { 0, 1 }, 1), new Pauli("YX", new int[] { 0, 1 }, 2)),
        2);
    SparsePauliOp observables21 = SparsePauliOp.fromSparseList(
        new Paulis(new Pauli("YX", new int[] { 0, 1 }, 2), new Pauli("XY", new int[] { 0, 1 }, 1)),
        2);
    SparsePauliOp observables22 = SparsePauliOp.fromSparseList(
        new Paulis(new Pauli("XZ", new int[] { 0, 1 }, 1), new Pauli("ZX", new int[] { 0, 1 }, 2)),
        2);
    SparsePauliOp observables31 = SparsePauliOp.fromSparseList(
        new Paulis(new Pauli("XX", new int[] { 0, 1 }, 2), new Pauli("YY", new int[] { 0, 1 }, 1)),
        2);
    SparsePauliOp observables32 = SparsePauliOp.fromSparseList(
        new Paulis(new Pauli("YY", new int[] { 0, 1 }, 1), new Pauli("ZZ", new int[] { 0, 1 }, 2)),
        2);
    EstimatorPUB pub1 = new EstimatorPUB.Builder().circuit(qasm).parameters(parms1)
        .observables(List.of(observables11.getPaulis(), observables12.getPaulis())).build();
    EstimatorPUB pub2 = new EstimatorPUB.Builder().circuit(qasm).parameters(parms2)
        .observables(List.of(observables21.getPaulis(), observables22.getPaulis())).build();
    EstimatorPUB pub3 = new EstimatorPUB.Builder().circuit(qasm).parameters(parms3)
        .observables(List.of(observables31.getPaulis(), observables32.getPaulis())).build();
    Job job = estimator.run(List.of(pub1, pub2, pub3));

    service.tags(job.getId(), new Tags(List.of("Broad", "Params", "Estimator")));

    // if (job != null) {
    // job.cancel();
    // }

    job = service.waitForFinalState(job.getId());

    if (job.isDone()) {
      PrimitiveResults results = service.jobResults(job.getId());
      dumpEvs(results);
    } else if (job.isError()) {
      System.out.println("Failed: " + job.getState().getReason());
    }
  }

  /**
   * Broadcast Estimator test with observables.
   */
  private void testEstimatorComplete() {
    Backend backend = service.backend(BACKEND);
    Estimator estimator = new Estimator(backend);
    String qasm = "OPENQASM 3.0;include \"stdgates.inc\";rz(pi/2) $0;sx $0;rz(pi/2) $0;"
        + "rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;";
    SparsePauliOp observables = SparsePauliOp.fromSparseList(
        new Paulis(new Pauli("XZ", new int[] { 0, 1 }, 1), new Pauli("ZX", new int[] { 0, 1 }, 2)),
        2);
    EstimatorPUB pub = new EstimatorPUB.Builder().circuit(qasm).observable(observables.getPaulis())
        .build();
    Job job = estimator.run(pub);

    job = service.waitForFinalState(job.getId());

    if (job.isDone()) {
      PrimitiveResults results = service.jobResults(job.getId());
      dumpEvs(results);
    } else if (job.isError()) {
      System.out.println("Failed: " + job.getState().getReason());
    }
  }

  /**
   * Broadcast Estimator test with parameters and observables.
   */
  private void testEstimatorParamsComplete() {
    Backend backend = service.backend(BACKEND);
    Estimator estimator = new Estimator(backend);
    String qasm = "OPENQASM 3.0;include \'stdgates.inc\';input float[64] theta;bit[2] c;"
        + "rz(pi/2) $0;sx $0;rz(pi) $0;rz(-pi/2) $1;rz(pi + theta) $1;sx $1;rz(5*pi/2) $1;"
        + "cz $1, $0;sx $0;rz(pi/2) $0;barrier $1, $0;c[0] = measure $1;c[1] = measure $0;";
    List<List<Double>> parms = List.of(List.of(3.14), List.of(1.57));
    SparsePauliOp observables = SparsePauliOp.fromSparseList(
        new Paulis(new Pauli("XZ", new int[] { 0, 1 }, 1), new Pauli("ZX", new int[] { 0, 1 }, 2)),
        2);
    EstimatorPUB pub = new EstimatorPUB.Builder().circuit(qasm).parameters(parms)
        .observable(observables.getPaulis()).build();
    Job job = estimator.run(pub);

    service.tags(job.getId(), new Tags(List.of("Params", "Estimator")));

    job = service.waitForFinalState(job.getId());

    if (job.isDone()) {
      PrimitiveResults results = service.jobResults(job.getId());
      dumpEvs(results);
    } else if (job.isError()) {
      System.out.println("Failed: " + job.getState().getReason());
    }
  }

  /**
   * Test Sampler (simulated).
   */
  private void testSamplerSimulate() {
    String circuit = null;
    try {
      FileInputStream fis = new FileInputStream("/home/genaro/divN_365.py");
      circuit = new String(fis.readAllBytes());
      fis.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (circuit != null) {
      SamplerResponse res = transpilation.sampler(circuit, 1024);

      if (res != null) {
        SamplerResponse.Result result = res.getResult();
        List<Map<Integer, Double>> dists = result.getQuasiDists();
        for (Map<Integer, Double> dist : dists) {
          Map<Integer, Double> dist2 = dist.entrySet().stream().sorted(Map.Entry.comparingByKey())
              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                  (oldVal, newVal) -> oldVal, LinkedHashMap::new));
          for (Integer key : dist2.keySet()) {
            System.out.println(key + ": " + dist.get(key));
          }
        }
      } else {
        System.out.println("Simulation failed!");
      }
    }
  }

  /**
   * Test Estimator (simulated).
   */
  private void testEstimatorSimulate() {
    String circuit = "qc = QuantumCircuit(2)\nqc.h(0)\nqc.cx(0,1)";
    String observable = "XY";

    if (circuit != null) {
      EstimatorResponse res = transpilation.estimator(circuit, observable);

      if (res != null) {
        List<Double> result = res.getResult();
        for (Double evs : result) {
          System.out.println(evs);
        }
      } else {
        System.out.println("Simulation failed!");
      }
    }
  }

  /**
   * Test Sampler.
   */
  private void testSampler() {
    Backend backend = service.backend(BACKEND);
    Sampler sampler = new Sampler(backend);
    String qasm = "OPENQASM 3.0;include \"stdgates.inc\";bit[2] c;rz(pi/2) $0;sx $0;"
        + "rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;"
        + "rz(pi/2) $1;c[0] = measure $0;c[1] = measure $1;";
    SamplerPUB pub = new SamplerPUB.Builder().circuit(qasm).shots(16).build();
    Job job = sampler.run(pub);

    service.job(job.getId()).getStatus();

    if (job != null) {
      job.cancel();
    }

  }

  /**
   * Test Sampler.
   */
  private void testSamplerX2() {
    Backend backend = service.backend(BACKEND);
    Sampler sampler = new Sampler(backend);
    String qasm = "OPENQASM 3.0;include \'stdgates.inc\';input float[64] theta;"
        + "bit[2] c1;bit[2] c2;rz(pi/2) $12;sx $12;rz(pi) $12;rz(-pi/2) $18;rz(pi + theta) $18;"
        + "sx $18;rz(5*pi/2) $18;cz $18, $12;sx $12;rz(pi/2) $12;barrier $18, $12;"
        + "c2[0] = measure $18;c2[1] = measure $12;";
    // String qasm = "OPENQASM 3.0;include \"stdgates.inc\";bit[2] c1;bit[2] c2;rz(pi/2) $0;"
    // + "sx $0;rz(pi/2) $0;c1[0] = measure $0;c1[1] = measure $1;rz(pi/2) $1;sx $1;"
    // + "rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;rz(pi/2) $1;"
    // + "c2[0] = measure $0;c2[1] = measure $1;";
    SamplerPUB pub = new SamplerPUB.Builder().circuit(qasm).shots(16).build();
    Job job = sampler.run(pub);

    service.job(job.getId()).getStatus();

    // if (job != null) {
    // job.cancel();
    // }

  }

  /**
   * Test Sampler.
   */
  private void testSamplerParams() {
    Backend backend = service.backend(BACKEND);
    Sampler sampler = new Sampler(backend);
    String qasm = "OPENQASM 3.0;include \'stdgates.inc\';input float[64] theta;bit[2] c;"
        + "rz(pi/2) $12;sx $12;rz(pi) $12;rz(-pi/2) $18;rz(pi + theta) $18;sx $18;"
        + "rz(5*pi/2) $18;cz $18, $12;sx $12;rz(pi/2) $12;barrier $18, $12;"
        + "c[0] = measure $18;c[1] = measure $12;";
    List<List<Double>> parms = List.of(List.of(3.14));
    SamplerPUB pub = new SamplerPUB.Builder().circuit(qasm).parameters(parms).shots(16).build();
    Job job = sampler.run(pub);

    service.job(job.getId()).getStatus();
  }

  /**
   * Test Sampler.
   */
  private void testSamplerParamsX2() {
    Backend backend = service.backend(BACKEND);
    Sampler sampler = new Sampler(backend);
    String qasm = "OPENQASM 3.0;include \'stdgates.inc\';input float[64] theta;"
        + "bit[2] c1;bit[2] c2;rz(pi/2) $12;sx $12;rz(pi) $12;rz(-pi/2) $18;"
        + "c1[0] = measure $18;c1[1] = measure $12;rz(pi + theta) $18;sx $18;"
        + "rz(5*pi/2) $18;cz $18, $12;sx $12;rz(pi/2) $12;barrier $18, $12;"
        + "c2[0] = measure $18;c2[1] = measure $12;";
    List<List<Double>> parms = List.of(List.of(0.0), List.of(1.57), List.of(3.14));
    SamplerPUB pub = new SamplerPUB.Builder().circuit(qasm).parameters(parms).shots(16).build();
    Job job = sampler.run(pub);

    service.job(job.getId()).getStatus();
  }

  /**
   * Test Sampler.
   */
  private void testSamplerComplete() {
    Backend backend = service.backend(BACKEND);
    Sampler sampler = new Sampler(backend);
    String qasm = "OPENQASM 3.0;include \"stdgates.inc\";bit[2] c;rz(pi/2) $0;sx $0;"
        + "rz(pi/2) $0;rz(pi/2) $1;sx $1;rz(pi/2) $1;cz $0, $1;rz(pi/2) $1;sx $1;"
        + "rz(pi/2) $1;c[0] = measure $0;c[1] = measure $1;";
    SamplerPUB pub = new SamplerPUB.Builder().circuit(qasm).shots(16).build();
    Job job = sampler.run(pub);

    job = service.waitForFinalState(job.getId());

    if (job.isDone()) {
      // Result results = service.jobResults(job.getId()).getResults().get(0);
      // SamplerData data = (SamplerData) results.getData();
      // Map<String, SamplerRegisters> registers = data.getRegisters();
      // for (String register : registers.keySet()) {
      // List<BitString> samples = registers.get(register).getSamples();
      // for (int i = 0; i < samples.size(); i++) {
      // BitString ss = samples.get(i);
      // System.out.println("\n\n\t" + register + "[" + i + "]");
      // for (String s : ss) {
      // System.out.println("\t" + s);
      // }
      // System.out.println("\n");
      // Map<String, Long> counts = ss.stream()
      // .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      // for (String key : counts.keySet()) {
      // Long count = counts.get(key);
      // System.out.println(key + ": " + count);
      // }
      // }
      // }
      PrimitiveResults results = service.jobResults(job.getId());
      if (results != null) {
        List<QError> errors = results.getErrors();
        if (errors == null) {
          dumpCounts(results);
        } else {
          for (QError error : errors) {
            System.out.println(error.getMessage());
          }
        }
      }
    }
  }

  /**
   * Test Sampler.
   */
  private void testSamplerParamsComplete() {
    Backend backend = service.backend(BACKEND);
    Sampler sampler = new Sampler(backend);
    String qasm = "OPENQASM 3.0;include \'stdgates.inc\';input float[64] theta;bit[2] c;"
        + "rz(pi/2) $12;sx $12;rz(pi) $12;rz(-pi/2) $18;rz(pi + theta) $18;sx $18;"
        + "rz(5*pi/2) $18;cz $18, $12;sx $12;rz(pi/2) $12;barrier $18, $12;"
        + "c[0] = measure $18;c[1] = measure $12;";
    List<List<Double>> parms = List.of(List.of(3.14), List.of(1.57), List.of(0.0));
    SamplerPUB pub = new SamplerPUB.Builder().circuit(qasm).parameters(parms).shots(16).build();
    Job job = sampler.run(pub);

    job = service.waitForFinalState(job.getId());

    if (job.isDone()) {
      PrimitiveResults results = service.jobResults(job.getId());
      if (results != null) {
        List<QError> errors = results.getErrors();
        if (errors == null) {
          dumpCounts(results);
        } else {
          for (QError error : errors) {
            System.out.println(error.getMessage());
          }
        }
      }
    }
  }

  /**
   * Jobs test.
   */
  public void testJobs() {

    Job job = service.jobDetails("d56hea8nsj9s73b3q13g");
    System.out.println(job);

    QResponse response = service.tags("d5439c3ht8fs739vgu0g", new Tags("test"));
    System.out.println(response);

    PrimitiveResults response2 = service.jobResults("xxd5439c3ht8fs739vgu0g");
    System.out.println(response2);

    QResponse response3 = service.cancelJob("d5439c3ht8fs739vgu0g");
    System.out.println(response3);

  }

  /**
   * Dump jobs.
   */
  public void dumpJobs() {

    System.out.println("ALL Jobs Dump");
    Jobs jobs = service.jobs();
    jobsDump(jobs);

    Map<YearMonth, Integer> totals = jobs.getJobs().stream().collect(Collectors.groupingBy(
        job -> YearMonth.from(Instant.parse(job.getCreated()).atZone(ZoneOffset.UTC).toLocalDate()),
        Collectors.summingInt(job -> job.getUsage().getQuantumSeconds())));

    for (YearMonth ym : totals.keySet()) {
      System.out.println(ym + ": " + totals.get(ym));
    }

  }

  /**
   * Backends test.
   */
  public void testBackends() {
    List<Backend> bb = service.backends(new BackendsRequest.Builder().build());
    // List<Backend> dd = service.backends(new BackendsRequest
    // .Builder()
    // .minNumQubits(156).build());
    // List<Backend> dd = service.backends(new BackendsRequest
    // .Builder().name("ibm_fez").build());

    for (Backend b : bb) {
      BackendStatus status = service.backendStatus(b.getName());
      BackendProps props = service.backendProps(b.getName());

      Map<String, Long> gates = props.getGates().stream()
          .collect(Collectors.groupingBy(Gate::getGate, Collectors.counting()));
      System.out.println(String.format("%s (%s): %s - %s", b.getName(), b.getQubits(),
          b.getQueueLength(), status.getStatus()));
      for (String gate : gates.keySet()) {
        Long count = gates.get(gate);
        System.out.println(String.format("\t%s: %d", gate, count));
      }
    }

  }

  /**
   * Dump jobs.
   */
  private void jobsDump(Jobs jobs) {
    for (Job job : jobs.getJobs()) {
      System.out.println(job);
    }
  }

  /**
   * Dump workloads.
   */
  private void workloadsDump(Workloads workloads) {
    for (Workload workload : workloads.getWorkloads()) {
      System.out.println(workload);
    }
  }

}
