package com.kartik.cs.impl;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.kartik.cs.SamplerInterface;

import com.kartik.cs.impl.Request;

public class CustomerSampler implements SamplerInterface {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("Please provide the input file full path as argument");
			System.exit(0);
		}

		// Initialize spark context
		SparkConf conf = new SparkConf().setAppName("com.kartik.cs.CustomerSampler").setMaster("local");
		JavaSparkContext context = new JavaSparkContext(conf);
System.out.println("000000000000000000000000test-------------------------");
		// Read input file
		// JavaRDD<Request> file = context.textFile(args[0]);

	}

	@Override
	public JavaRDD<Request> sampleRequests(JavaRDD<Request> requests) {
		// TODO Auto-generated method stub
		return null;
	}

}
