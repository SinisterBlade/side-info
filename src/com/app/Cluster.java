package com.app;

import java.util.ArrayList;
import java.util.HashMap;

import com.bean.KDocument;

public class Cluster {
	int id;
	ArrayList<KDocument> documents;
	HashMap<String, Double> centroid;
	ArrayList<String> vectors;
	
	public Cluster(int id, ArrayList<String> vectors) {
		this.documents = new ArrayList<KDocument>();
		this.centroid = new HashMap<String, Double>();
		this.id = id;
		this.vectors = vectors;
	}
	
	public void addDocument(KDocument doc) {
		documents.add(doc);
		System.out.println("Added document " + doc.getId() + " to cluster " + id);
	}
	
	public void removeDocument(int docID) {
		boolean isRemoved = false;
		for (KDocument d : documents) {
			if(d.getId() == docID) {
				isRemoved = documents.remove(d);
				System.out.println("Removed document " + docID + " from cluster " + id);
				break;
			}
		}
		if(!isRemoved) {
			System.out.println("Document " + docID + " not removed!");
		}
	}
	
	public void calculateCentroid() {
		int size = documents.size();
		for(String term : vectors) {
			double value = 0;
			for (KDocument doc : documents) {
				value += doc.getTfIdf().get(term);
			}
			System.out.println("Total " + term + ": " + value);
			value /= size;
			centroid.put(term, value);
			System.out.println("Centroid " + term + ": " + value);
		}
	}
	
}
