package com.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class GoogleLinkRetriever {
	private  ArrayList<String> links;
	private  String startPage;
	private  String googleSearch;
	public GoogleLinkRetriever() {
		links = new ArrayList<String>();
		startPage = "&start=0";
		googleSearch = "http://www.google.com/search?q=";
	}
	public  ArrayList<String> getLinks(String query) throws IOException {
		links = new ArrayList<String>();
		Document doc = Jsoup.connect(googleSearch + query).userAgent("Chrome").get();
		Elements elements = doc.select(".r a");
		String pattern = "(/url\\?q=)(http.*)(&sa.*)";
		Pattern r = Pattern.compile(pattern);
		for (Element e : elements) {
			Matcher m = r.matcher(e.attr("href"));
			if (m.find()) {
				String url = m.group(2);
				System.out.println("URL: " + url);
				links.add(url);
			}
		}
		return links;
	}
	
	public  ArrayList<String> getLinksFromPage(String query, int page) {
		// TODO
		return null;
	}
}
