package com.gnf.qrest.builders;

import java.util.List;

public sealed interface PUB 
	permits SamplerPUB, EstimatorPUB {

	String circuit();
	List<List<Double>> parameters();
//	List<Object> buildAsList();
	
}
