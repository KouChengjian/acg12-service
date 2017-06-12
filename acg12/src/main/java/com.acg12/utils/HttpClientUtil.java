package com.acg12.utils;

import com.acg12.config.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;

/**
 * Created by Administrator on 2017/6/1.
 */
public class HttpClientUtil {

    String mUrl;

    public HttpClientUtil(String mUrl){
        this.mUrl = mUrl;
    }

    public HttpClientUtil init(String mUrl){
        return new HttpClientUtil(mUrl);
    }

    public String get(){
        String content = "";
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10 * 1000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("请求url失败");
            InputStream is = conn.getInputStream();//拿到输入流
            if ("gzip".equals(conn.getContentEncoding())) {
                content = StringUtil.readDataForZgip(is, "utf-8");
            }else{
                content = StringUtil.readDataForZgip(is);
            }
            conn.disconnect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public String post(){
        String content = "";
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置参数
            conn.setConnectTimeout(10 * 1000);
            conn.setDoOutput(true);   //需要输出
            conn.setDoInput(true);   //需要输入
            conn.setUseCaches(false);  //不允许缓存
            conn.setRequestMethod("POST");   //设置POST方式连接
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            conn.setRequestProperty("Charset", "UTF-8");
            conn.connect();
            PrintWriter printWriter = new PrintWriter(conn.getOutputStream());
            String param="name="+ URLEncoder.encode("丁丁","UTF-8");
            printWriter.write(param);
            printWriter.flush();
            printWriter.close();
            int resultCode = conn.getResponseCode();
            if(HttpURLConnection.HTTP_OK != resultCode){
                throw new RuntimeException("请求url失败");
            }
            InputStream is = conn.getInputStream();//拿到输入流

            if ("gzip".equals(conn.getContentEncoding())) {
                content = StringUtil.readDataForZgip(is, "utf-8");
            }else{
                content = StringUtil.readDataForZgip(is);
            }
            conn.disconnect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }




}
