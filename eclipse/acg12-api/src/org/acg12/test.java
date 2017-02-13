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
		String url = "http://www.bilibili.com/mobile/video/av8192521.html";
		try {
			Document document = Jsoup.connect(url).ignoreContentType(true)
					.timeout(50000).get();
			if (document.toString() != null && !document.toString().isEmpty()) {
				System.out.println(document.body());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		sendShortMessage();
	}
	
	static String url1 = "http://api.bilibili.com/playurl?callback=jQuery172027843182437155334_1486280516331&aid=8367843&page=1&platform=html5&quality=1&vtype=mp4&type=jsonp&token=914e860195f75f314c08b49c375e9958&_=1486280516394";
	
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
