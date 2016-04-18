package com.app;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLProtocolException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DocumentScraper {
	private String url;
	private Document document;
	public DocumentScraper(String url) throws SocketException, UnsupportedMimeTypeException ,UnknownHostException, SSLProtocolException, SSLHandshakeException, SocketTimeoutException, IOException, HttpStatusException {
		this.url = url;
		this.document = Jsoup.connect(url).userAgent("Chrome").get();
	}
	
	public String getHTML() {
		return document.html();
	}
	
	public String getAllParagraphs() {
		Elements paragraphElements = document.select("p");
		StringBuilder content = new StringBuilder();
		for (Element p : paragraphElements) {
			content.append(p.text());
			content.append("\n");
		}
		return content.toString();
	}
	
	public String getFirstParagraph() {
		Element paragraphElement = document.select("p").first();
		if (paragraphElement != null) {
			String paragraph = paragraphElement.text();
			return paragraph;
		}
		else return null;
	}
	
	public ArrayList<String> getLinksFromFirstParagraph() {
		ArrayList<String> linkArray = new ArrayList<String>();
		Element firstPara = document.select("p").first();
		if (firstPara == null) {
			return linkArray;
		}
		Elements links = firstPara.select("a[href]");
		String pattern = "(http[^#\\n]*)";
		Pattern r = Pattern.compile(pattern);	
		for (Element link : links) {
			Matcher m = r.matcher(link.attr("abs:href"));
			if(m.find()) {
				String baseUrl = m.group(1);
				linkArray.add(baseUrl);
			}
		}
		return linkArray;
	}
	
	public ArrayList<String> getAllLinks() {
		ArrayList<String> linkArray = new ArrayList<String>();
		Elements paragraphElements = document.select("p");
		if (paragraphElements == null) {
			return linkArray;
		}
		for (Element p : paragraphElements) {
			Elements links = p.select("a[href]");
			String pattern = "(http[^#\\n]*)";
			Pattern r = Pattern.compile(pattern);	
			for (Element link : links) {
				Matcher m = r.matcher(link.attr("abs:href"));
				if(m.find()) {
					String baseUrl = m.group(1);
					linkArray.add(baseUrl);
				}
			}
		}
		return linkArray;
	}
}
