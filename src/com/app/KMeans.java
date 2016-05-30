package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bean.KDocument;

/**
 * Class contains all logic for clustering {@link com.bean.KDocument KDocuments} using KMeans 
 * @author Rajat
 *
 */
public class KMeans {
	final static double MAX_DISTANCE = 99999999;
	int totalDocuments;
	int k;
	ArrayList<KDocument> docList;
	ArrayList<Cluster> clusters;
	ArrayList<String> vectors;
	HashMap<Integer, Cluster> clusterMap;
	
	public KMeans(int k, ArrayList<KDocument> docs, ArrayList<String> vectors) {
		this.k = k;
		this.docList = docs;
		this.vectors = vectors;
		this.totalDocuments = docs.size();
		clusters = new ArrayList<Cluster>();
		clusterMap = new HashMap<Integer, Cluster>();
	}
	
	/**
	 * Method to be called to start the clustering process
	 * @return List of {@link com.app.Cluster Clusters}
	 */
	public ArrayList<Cluster> startKMeaning() {
		initialize();
		//printClusters();
		int iteration = 0;
		boolean converged = false;
		while(!converged) {
			System.out.println("Iteration: " + ++iteration);
			converged = update();
		}
		return clusters;
	}
	
	/**
	 * Initializes the KMeans clustering using Random Partition method
	 */
	private void initialize() {
		int documentsInEachCluster = (totalDocuments / k) + 1;
		Iterator<KDocument> iter = docList.iterator();
		for(int i = 1; i <= k; i++) {
			Cluster cluster = new Cluster(i, vectors);
			for (int j = 0; j < documentsInEachCluster; j++) {
				if(iter.hasNext()) {
					assignCluster(iter.next(), cluster);
				}
				else {
					break;
				}
			}
			clusters.add(cluster);
			clusterMap.put(cluster.getId(), cluster);
		}
	}
	
	/**
	 * Steps to be performed on each iteration of KMeans
	 * @return True if clusters have converged
	 */
	private boolean update() {
		boolean converged = true;
		//Calculate centroids
		for (Cluster cluster : clusters) {
			cluster.calculateCentroid();
			System.out.println("Centroid " + cluster.getId() + ": " + cluster.getCentroid());
		}
		//Calculate Distances
		for(KDocument doc : docList) {
			double min = MAX_DISTANCE;
			Cluster minDistanceCluster = null;
			for (Cluster cluster : clusters) {
				double distance = calculateEuclideanDistance(doc, cluster);
				if (distance < min) {
					min = distance;
					minDistanceCluster = cluster;
				}
			}
			if(minDistanceCluster != null) {
				Cluster originalCluster = clusterMap.get(doc.getClusterID());
				if(originalCluster.getId() == minDistanceCluster.getId()) {
					System.out.println(doc.getId() + ": Same cluster!");
				}
				else {
					converged = false;
					originalCluster.removeDocument(doc.getId());
					assignCluster(doc, minDistanceCluster);
					System.out.println("Moving document " + doc.getId() + " from cluster " + originalCluster.getId() + " to cluster " + minDistanceCluster.getId());
				}
			}
			else {
				throw new NullPointerException();
			}
		}
		//printClusters();
		return converged;
	}
	
	/**
	 * Calculates Euclidean distance between specified document and cluster centroid
	 * @param doc 
	 * @param cluster
	 * @return Distance between the Document and Cluster
	 */
	private double calculateEuclideanDistance(KDocument doc, Cluster cluster) {
		HashMap<String, Double> vector1 = doc.getTfIdf();
		HashMap<String, Double> vector2 = cluster.getCentroid();
		double sumOfSquares = 0;
		for (String term : vectors) {
			double vectorDifference = vector1.get(term) - vector2.get(term);
			sumOfSquares += (vectorDifference * vectorDifference);
		}
		return Math.sqrt(sumOfSquares);
	}
	
	/**
	 * Calculates Cosine distance between specified document and cluster centroid
	 * @param doc
	 * @param cluster
	 * @return Distance between the Document and Cluster
	 */
	private double calculateCosineDistance (KDocument doc, Cluster cluster) {
		HashMap<String, Double> vector1 = doc.getTfIdf();
		HashMap<String, Double> vector2 = cluster.getCentroid();
		double dotProduct = 0;
		double magnitudeOfVector1 = 0;
		double magnitudeOfVector2 = 0;
		for (String term: vectors) {
			double termOfVector1 = vector1.get(term);
			double termOfVector2 = vector2.get(term);
			dotProduct += (termOfVector1 * termOfVector2);
			magnitudeOfVector1 += (termOfVector1 * termOfVector1);
			magnitudeOfVector2 += (termOfVector2 * termOfVector2);
		}
		magnitudeOfVector1 = Math.sqrt(magnitudeOfVector1);
		magnitudeOfVector2 = Math.sqrt(magnitudeOfVector2);
		if (magnitudeOfVector1 == 0 || magnitudeOfVector2 == 0 || dotProduct == 0) {
			return 0;
		}
		return 1 - (dotProduct / (magnitudeOfVector1 * magnitudeOfVector2));
	}
	
	/**
	 * Assign a document to some cluster
	 * @param doc
	 * @param cluster
	 */
	private void assignCluster(KDocument doc, Cluster cluster) {
		cluster.addDocument(doc);
		doc.setClusterID(cluster.getId());
	}
	
	/**
	 * Pretty print all clusters
	 */
	private void printClusters() {
		for (Cluster c : clusters) {
			c.listDocuments();
		}
	}
}
