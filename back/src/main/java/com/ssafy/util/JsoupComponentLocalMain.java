package com.ssafy.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupComponentLocalMain {
	
	public static Map<String, Map<String, String>> getMarketNews() {

		final String marketURL = "https://land.naver.com/news/field.naver?news_type_cd=10";
		
		Connection conn = Jsoup.connect(marketURL);
		Map<String, Map<String, String>> arr = new HashMap<String, Map<String, String>>();
		try {
			Document document = conn.get();
			Elements newsList = document.select("div.section_headline ul.headline_list li");
			StringBuilder sb = new StringBuilder();
			int index = 0;
			for (Element element : newsList) {
				Map<String, String> obj = new HashMap<String, String>();
				obj.put("link", "land.naver.com"+element.select("dt.photo a").attr("href"));
				obj.put("img", element.select("dt.photo img").attr("src"));
				obj.put("subject", element.select("dt.photo img").attr("alt"));
				obj.put("content", element.select("dd").text());
				arr.put(index+"", obj);
				if(index++ == 2) break;
			}
		} catch (IOException ignored) {
		}
		return arr;
	}
	
	public static Map<String, Map<String, String>> getPolicyNews() {

		final String policyURL = "https://land.naver.com/news/field.naver?news_type_cd=20";
		
		Connection conn = Jsoup.connect(policyURL);
		Map<String, Map<String, String>> arr = new HashMap<String, Map<String, String>>();
		try {
			Document document = conn.get();
			Elements newsList = document.select("div.section_headline ul.headline_list li");
			StringBuilder sb = new StringBuilder();
			int index = 0;
			for (Element element : newsList) {
				Map<String, String> obj = new HashMap<String, String>();
				obj.put("link", "land.naver.com"+element.select("dt.photo a").attr("href"));
				obj.put("img", element.select("dt.photo img").attr("src"));
				obj.put("subject", element.select("dt.photo img").attr("alt"));
				obj.put("content", element.select("dd").text());
				arr.put(index+"", obj);
				if(index++ == 2) break;
			}
		} catch (IOException ignored) {
		}
		return arr;
	}
	
	public static Map<String, Map<String, String>> getHouseNews() {
		final String houseURL = "https://land.naver.com/news/field.naver?news_type_cd=50";
		
		Connection conn = Jsoup.connect(houseURL);
		Map<String, Map<String, String>> arr = new HashMap<String, Map<String, String>>();
		try {
			Document document = conn.get();
			Elements newsList = document.select("div.section_headline ul.headline_list li");
			StringBuilder sb = new StringBuilder();
			int index = 0;
			for (Element element : newsList) {
				Map<String, String> obj = new HashMap<String, String>();
				obj.put("link", "land.naver.com"+element.select("dt.photo a").attr("href"));
				obj.put("img", element.select("dt.photo img").attr("src"));
				obj.put("subject", element.select("dt.photo img").attr("alt"));
				obj.put("content", element.select("dd").text());
				arr.put(index+"", obj);
				if(index++ == 2) break;
			}
		} catch (IOException ignored) {
		}
		return arr;
	}
}
