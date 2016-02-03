package com.bean;

import java.util.HashMap;

public class KDocument {
	private int id;
	private HashMap<String, Double> tfIdf;
	private HashMap<String, Double> termFrequency;
	
	public KDocument(int id) {
		// TODO Auto-generated constructor stub
		tfIdf = new HashMap<String, Double>();
		termFrequency = new HashMap<String, Double>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HashMap<String, Double> getTfIdf() {
		return tfIdf;
	}
	public void setTfIdf(HashMap<String, Double> tfIdf) {
		this.tfIdf = tfIdf;
	}
	public HashMap<String, Double> getTermFrequency() {
		return termFrequency;
	}
	public void setTermFrequency(HashMap<String, Double> termFrequency) {
		this.termFrequency = termFrequency;
	}
	
	
}
