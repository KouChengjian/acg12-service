package org.acg12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class test {

	public static void main(String[] args) {
//		String url = String.format("http://api.bilibili.com/playurl?callback=jQuery172024279008170264427_1478221268354&aid=%s", "7953903");
		//String url = "http://www.bilibili.com/mobile/list/default-24-1-2017-01-05~2017-01-12.html";
		//System.out.println(url);
		Document document = null;
		try {
//			document = Jsoup.connect(url).ignoreContentType(true)
//					.timeout(50000).get();
//			if (document.toString() != null && !document.toString().isEmpty()) {
//				System.out.println(document.body());
//			}
			
			document = Jsoup.connect(url1).ignoreContentType(true)
					.timeout(50000).get();
			if (document.toString() != null && !document.toString().isEmpty()) {
				System.out.println(document.body());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//sendShortMessage();
	}
	
	static String url1 = "http://api.bilibili.com/playurl?callback=jQuery172015206836021925452_1484185935143"
			+ "&aid=7964991&page=1&platform=html5&quality=1&vtype=mp4&type=jsonp&token=256341f8a00324fb4343aaf0ee182a32&_=1484185935306";
	
	public static String sendShortMessage() {
		String line = "";
		StringBuffer sb = new StringBuffer();
		try {
			URL u = new URL(url1);
			URLConnection uc = u.openConnection();
			uc.setDoOutput(true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			while ((line = in.readLine()) != null) {
				sb.append(line);
				System.err.println("readLine: " + sb.toString());
				// 此处获得即为跨域访问的返回值，这次测试获取的是JSON格式的字串
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(sb);
	}

}
