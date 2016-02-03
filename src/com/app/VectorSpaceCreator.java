package com.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VectorSpaceCreator {
	String originalString;
	static final String[] stopWordsArray = {"a","able","about","across","after","all","almost","also","am","among","an","and","any","are","as","at","be","because","been","but","by","can","cannot","could","dear","did","do","does","either","else","ever","every","for","from","get","got","had","has","have","he","her","hers","him","his","how","however","i","if","in","into","is","it","its","just","least","let","like","likely","may","me","might","most","must","my","neither","no","nor","not","of","off","often","on","only","or","other","our","own","rather","said","say","says","she","should","since","so","some","than","that","the","their","them","then","there","these","they","this","tis","to","too","twas","us","wants","was","we","were","what","when","where","which","while","who","whom","why","will","with","would","yet","you","your"};
	static List<String> stopWords = Arrays.asList(stopWordsArray);
	public VectorSpaceCreator(String original) {
		this.originalString = original;
	}
	
	public List<String> getVectorSpace() {
		String[] words = originalString.split(" ");
		ArrayList<String> originalWords =new ArrayList<String>(Arrays.asList(words));
		ArrayList<String> vectors = new ArrayList<String>();
		vectors.add(originalString);
		int length = originalWords.size();
		for(int i = 0; i < length; i++) {
			String word = originalWords.get(i);
			if(!stopWords.contains(word) && !vectors.contains(word)) {
				vectors.add(word);
			}
		}
		return vectors;
		
	}
}
