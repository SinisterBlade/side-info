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
	
	public ArrayList<String> getAllParagraphs() {
		Elements paragraphElements = document.select("p");
		ArrayList<String> paragraphs = new ArrayList<String>();
		for (Element p : paragraphElements) {
			paragraphs.add(p.text());
		}
		return paragraphs;
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
		Element firstPara = document.select("p").first();
		Elements links = firstPara.select("a[href]");
		ArrayList<String> linkArray = new ArrayList<String>();
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
		Elements links = document.select("a[href]");
		ArrayList<String> linkArray = new ArrayList<String>();
		for (Element link : links) {
			System.out.println(link.attr("abs:href"));
			linkArray.add(link.attr("abs:href"));
		}
		return linkArray;
	}
}
