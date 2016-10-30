package com.comparator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import com.app.Cluster;

/**
 * Specifies how 2 clusters are supposed to be compared
 * @author Rajat
 *
 */
public class ClusterComparator implements Comparator<Cluster> {

	@Override
	public int compare(Cluster c1, Cluster c2) {
		HashMap<String, Double> centroid1 = c1.getCentroid();
		HashMap<String, Double> centroid2 = c2.getCentroid();
		double totalTfIdf1 = 0;
		double totalTfIdf2 = 0;
		Iterator<Double> iterator1 = centroid1.values().iterator();
		while(iterator1.hasNext()) {
			totalTfIdf1 += iterator1.next(); 
		}
		Iterator<Double> iterator2 = centroid2.values().iterator();
		while(iterator2.hasNext()) {
			totalTfIdf2 += iterator2.next(); 
		}
		//System.out.println("Cluster " + c1.getId() + ": " + totalTfIdf1);
		//System.out.println("Cluster " + c2.getId() + ": " + totalTfIdf2);
		
		
		//Second cluster before first for descending order
		return Double.compare(totalTfIdf2, totalTfIdf1);		
	}
}
