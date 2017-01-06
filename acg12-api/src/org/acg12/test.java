package org.acg12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class test {

	public static void main(String[] args) {
		String url = String.format(Constant.URL_PLAY_VIDEO_INFO, "7315442");
		System.out.println(url);
		Document document = null;
		try {
			document = Jsoup.connect(url).ignoreContentType(true)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36").cookie("auth", "token")
					.timeout(50000).get();
			if (document.toString() != null && !document.toString().isEmpty()) {
				System.out.println(document.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendShortMessage();
	}
	
	static String url = "http://api.bilibili.com/playurl?callback=jQuery172024279008170264427_1478221268354&aid=7315442&page=1&platform=html5&quality=1&vtype=mp4&type=jsonp&_=1478221268411";
	
	public static String sendShortMessage() {
		String line = "";
		  StringBuffer sb = new StringBuffer();
		  
		  try {
		   
		   URL u = new URL(url);
		   URLConnection uc = u.openConnection();
		   
		   uc.setDoOutput(true);
		   
		   BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		   
		   while ((line = in.readLine()) != null) {
		    
		    sb.append(line);

		    System.err.println("readLine: " + sb.toString());
		    // 此处获得即为跨域访问的返回值，这次测试获取的是JSON格式的字串
		   }
		  
		   in.close();
		  }
		  catch (Exception e) {
		   
		   e.printStackTrace();
		  }
		  
		  return new String(sb);
		 }

}
