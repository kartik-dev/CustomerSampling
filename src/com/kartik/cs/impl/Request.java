package com.kartik.cs.impl;

public class Request implements java.io.Serializable{
	
	public Request(String requestID, int userID, int customerID, String userAgent, String url, long timestamp) {
		super();
		this.requestID = requestID;
		this.userID = userID;
		this.customerID = customerID;
		this.userAgent = userAgent;
		this.url = url;
		this.timestamp = timestamp;
	}

	private String requestID;
	private int userID;
	private int customerID;
	private String userAgent;
	private String url;
	private long timestamp;

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}