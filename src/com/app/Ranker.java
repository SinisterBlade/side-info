package com.app;

import java.util.ArrayList;
import java.util.Iterator;

import com.comparator.ClusterComparator;

public class Ranker {
	
	public static void rankClusters(ArrayList<Cluster> clusters) {
		clusters.sort(new ClusterComparator());
	}
	
	public static void removeEmpty(ArrayList<Cluster> clusters) {
		Iterator<Cluster> iterator = clusters.iterator();
		while(iterator.hasNext()) {
			Cluster c = iterator.next();
			if(c.getDocuments().isEmpty()) {
				iterator.remove();
			}
		}
	}
	
}
