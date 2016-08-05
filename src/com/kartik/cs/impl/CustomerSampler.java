package com.kartik.cs.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import com.kartik.cs.SamplerInterface;

import scala.Tuple2;

public class CustomerSampler implements SamplerInterface, Serializable {

	static JavaSparkContext context;

	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("Please provide the input file full path as argument");
			System.exit(0);
		}

		// Initialize spark context
		SparkConf conf = new SparkConf().setAppName("com.kartik.cs.CustomerSampler").setMaster("local");
		context = new JavaSparkContext(conf);

		// Map csv file to Request object
		JavaRDD<Request> customerRequestsRDD = context.textFile(args[0]).map(new Function<String, Request>() {
			public Request call(String line) throws Exception {
				String[] fields = line.split(",");
				Request sd = new Request(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), fields[3],
						fields[4], Long.parseLong(fields[5]));
				return sd;
			}
		}).cache();

		JavaRDD<Request> sampleOutput = new CustomerSampler().sampleRequests(customerRequestsRDD);
		System.out.println("Sample Count:" + sampleOutput.count());
	}

	@Override
	public JavaRDD<Request> sampleRequests(JavaRDD<Request> requests) {
		// Group by Customer ID, so that the sample can be taken from every distinct customer
		JavaPairRDD<Integer, Iterable<Request>> customerGroup = requests.groupBy(new Function<Request, Integer>() {
			@Override
			public Integer call(Request request) throws Exception {
				return request.getCustomerID();
			}
		});

		// Sample by distinct customer
		// FIXME: Sample list needs to be transformed to JavaRDD<Request>
		List<Tuple2<Integer, Iterable<Request>>> sampleList = customerGroup.takeSample(false, 50000);

		// Print output for testing
		for (Tuple2<?, ?> tuple : sampleList) {
			System.out.println("Output :" + tuple._1() + ": " + tuple._2());
		}

		// FIXME: This code is to test sample function. It doesn't consider
		// customer group and takes the sample from whole dataset
		int count = (int) requests.count();
		List<Request> sampleOutput = requests.takeSample(false, count >= 50000 ? 50000 : count);
		JavaRDD<Request> output = context.parallelize(sampleOutput);

		return output;
	}
}
