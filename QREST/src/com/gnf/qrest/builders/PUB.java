package com.gnf.qrest.builders;

import java.util.List;
import java.util.Map;

public sealed interface PUB 
	permits SamplerPUB, EstimatorPUB {

	String circuit();
	List<List<Double>> parameters();
	List<Object> buildAsList();
	
}
