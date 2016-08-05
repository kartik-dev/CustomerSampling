package com.kartik.cs;

import org.apache.spark.api.java.JavaRDD;
import com.kartik.cs.impl.Request;

public interface SamplerInterface {
	JavaRDD<Request> sampleRequests(JavaRDD<Request> requests);
}
