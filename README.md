# Customer Sampler
The objective is that the function sampleRequests should do the following:
Given an RDD of all requests, it should produce and return a ( sample without replacement) in the
form of a JavaRDD<Request> that contains 50.000 (Fifty Thousand) requests for each distinct
customerID. Of course if a customerID has less than 50.000 requests, then all of them must be
present in the sample. 

## Build Instructions

cd $PROJECT_HOME/mvn clean compile package

$SPARK_HOME/bin/spark-submit --class com.kartik.cs.impl.CustomerSampler  --master local[2] /root/workspace/CustomerSampling/target/CustomerSampling-0.0.1.jar /root/workspace/CustomerSampling/src/resources/requests.txt

