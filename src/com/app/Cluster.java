package com.app;

import java.util.ArrayList;
import java.util.HashMap;

import com.bean.KDocument;

public class Cluster {
	private int id;
	private ArrayList<KDocument> documents;
	private HashMap<String, Double> centroid;
	private ArrayList<String> vectors;
	
	public Cluster(int id, ArrayList<String> vectors) {
		this.documents = new ArrayList<KDocument>();
		this.centroid = new HashMap<String, Double>();
		this.id = id;
		this.vectors = vectors;
	}
	
	public void addDocument(KDocument doc) {
		documents.add(doc);
		//System.out.println("Added document " + doc.getId() + " to cluster " + id);
	}
	
	public void removeDocument(int docID) {
		boolean isRemoved = false;
		for (KDocument d : documents) {
			if(d.getId() == docID) {
				isRemoved = documents.remove(d);
				//System.out.println("Removed document " + docID + " from cluster " + id);
				break;
			}
		}
		if(!isRemoved) {
			System.out.println("Document " + docID + " not removed!");
		}
	}
	
	public void calculateCentroid() {
		int size = documents.size();
		if(size > 0) {
			for(String term : vectors) {
				double value = 0;
				for (KDocument doc : documents) {
					value += doc.getTfIdf().get(term);
				}
				//System.out.println("Total " + term + ": " + value);
				value /= size;
				centroid.put(term, value);
				//System.out.println("Centroid " + term + ": " + value);
			}
		}
		else {
			for(String term : vectors) {
				centroid.put(term, 0.0);
			}
		}
	}
	
	public void listDocuments() {
		System.out.println("Cluster " + id + ":");
		for(KDocument doc : documents) {
			System.out.println("  " + doc.getId());
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HashMap<String, Double> getCentroid() {
		return centroid;
	}
	
	
}
