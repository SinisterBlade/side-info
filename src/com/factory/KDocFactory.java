package com.factory;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bean.KDocument;
import com.dao.DAOInterface;

import oracle.sql.CLOB;

/**
 * This class generates {@link com.bean.KDocument} from the database contents
 * @author Rajat
 *
 */
public class KDocFactory {
	DAOInterface dao;
	ArrayList<String> vectors;
	public KDocFactory(DAOInterface dao, ArrayList<String> vectors) {
		this.dao = dao;
		this.vectors = vectors;
	}
	
	/**
	 *
	 * @param term Term whose frequency is to be determined
	 * @param content Body of text to be considered
	 * @return Term Frequency of specified term
	 */
	public double getTF(String term, String content) {
		Pattern p = Pattern.compile(term);
		Matcher m = p.matcher(content);
		int count = 0;
		while(m.find()) {
			count++;
		}
		int totalWords = content.trim().split("\\s+").length;
		//System.out.println("Total: " + totalWords);
		double tf = (double) count / totalWords;
		//System.out.println("TF: " + tf);
		return tf;
	}
	
	/**
	 * 
	 * @param term Term whose IDF is to be determined
	 * @return Inverse Document Frequency of specified term
	 */
	public double getIDF(String term) {
		int frequency = dao.getFrequency(term);
		//System.out.println("Frequency " + term + ": " + frequency);
		if(frequency == 0) {
			//System.out.println(term + " IDF: " + 0.0);
			return 0;
		}
		int total = dao.countDocuments();
		double idf = Math.log((double) total / frequency);
		//System.out.println(term + " IDF: " + idf);
		return idf;
	}
	
	/**
	 * 
	 * @return A Map of every term with its Inverse Document Frequency
	 */
	public HashMap<String, Double> getIDFVector() {
		HashMap<String, Double> IDFVector = new HashMap<String, Double>();
		for (String term : vectors) {
			IDFVector.put(term, getIDF(term));
		}
		return IDFVector;
	}
	
	/**
	 * 
	 * @return List containing all KDocuments generated from database contents
	 */
	public ArrayList<KDocument> getKDocs() {
		ArrayList<KDocument> docList = new ArrayList<KDocument>();
		HashMap<String, Double> IDF = getIDFVector();
		Clob contentClob;
		ResultSet rs = dao.getAllContent();
		try {
			while(rs.next()) {
				int id = rs.getInt(1);
				contentClob = rs.getClob(2);
				String content = contentClob.getSubString(1, (int)contentClob.length());
				HashMap<String, Double> tfidfVec = new HashMap<String, Double>();
				for (String term : vectors) {
					double tf = getTF(term, content);
					double tfidf = tf * IDF.get(term);
					tfidfVec.put(term, tfidf);
				}
				KDocument kDoc = new KDocument(id);
				kDoc.setTfIdf(tfidfVec);
				docList.add(kDoc);
			}		
			return docList;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
