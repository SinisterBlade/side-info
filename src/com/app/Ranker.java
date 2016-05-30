package com.app;

import java.util.ArrayList;
import java.util.Iterator;

import com.comparator.ClusterComparator;

/**
 * Class to rank clusters
 * @author Rajat
 *
 */
public class Ranker {
	
	/**
	 * Rank clusters specified by {@link com.comparator.ClusterComparator}
	 * @param clusters List of clusters to be ranked
	 */
	public static void rankClusters(ArrayList<Cluster> clusters) {
		clusters.sort(new ClusterComparator());
	}
	
	/**
	 * Remove empty clusters
	 * @param clusters List of clusters from which empty clusters are to be removed
	 */
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
