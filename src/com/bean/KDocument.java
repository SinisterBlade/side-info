package com.bean;

import java.util.HashMap;

/**
 * Class that stores text document in a numeric format using TF-IDF
 * @author Rajat
 *
 */
public class KDocument {
	private int id;
	private HashMap<String, Double> tfIdf;
	private int clusterID;
	
	public KDocument(int id) {
		tfIdf = new HashMap<String, Double>();
		this.id = id;
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

	public int getClusterID() {
		return clusterID;
	}

	public void setClusterID(int clusterID) {
		this.clusterID = clusterID;
	}
	
	
	
}
