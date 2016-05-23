package com.purplelight.mcm.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.purplelight.mcm.api.config.ConfigUtil;

public class HttpUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public final static int GET = 0;

    public final static int POST = 1;

    public static String GetDataFromNet(String strUrl, HashMap<String, String> params, int type){
        try {
            if (type == GET) {
                return GetDataFromNet(strUrl, params);
            } else if (type == POST) {
                return PostDataFromNet(strUrl, params);
            } else {
                return GetDataFromNet(strUrl, params);
            }
        }catch (Exception ex){
        	logger.error(ex.getMessage(), ex);
        	ex.printStackTrace();
        }
        return "";
    }

    public static String PostJosn(String strUrl, String json) throws IOException{
    	int timeOut = Integer.parseInt(ConfigUtil.config.getProperty("connection_time_out"));
    	
        String result = "";
        URL url = new URL(strUrl);

        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setConnectTimeout(timeOut);
        urlConnection.setReadTimeout(timeOut);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setUseCaches(false);
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Charset", "utf-8");

        urlConnection.connect();

        DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
        dataOutputStream.writeBytes(json);
        dataOutputStream.flush();
        dataOutputStream.close();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String readLine;
        while ((readLine = bufferedReader.readLine()) != null){
            result += readLine;
        }
        bufferedReader.close();
        urlConnection.disconnect();

        return result;
    }

    private static String PostDataFromNet(String strUrl, HashMap<String, String> params) throws IOException {
    	int timeOut = Integer.parseInt(ConfigUtil.config.getProperty("connection_time_out"));
    	
        String result = "";
        URL url = new URL(strUrl);

        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setConnectTimeout(timeOut);
        urlConnection.setReadTimeout(timeOut);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setUseCaches(false);
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("Charset", "utf-8");

        urlConnection.connect();

        DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
        for(Entry<String, String> entry : params.entrySet()){
            dataOutputStream.writeBytes(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "utf-8"));
            dataOutputStream.writeBytes("&");
        }
        dataOutputStream.flush();
        dataOutputStream.close();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String readLine;
        while ((readLine = bufferedReader.readLine()) != null){
            result += readLine;
        }
        bufferedReader.close();
        urlConnection.disconnect();

        return result;
    }

    private static String GetDataFromNet(String strUrl, HashMap<String, String> params) throws IOException {
    	int timeOut = Integer.parseInt(ConfigUtil.config.getProperty("connection_time_out"));
    	
        String result = "";
        URL url = new URL(generateGetUrl(strUrl, params));

        HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
        urlConn.setConnectTimeout(timeOut);
        urlConn.setReadTimeout(timeOut);
        urlConn.connect();

        InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(in);

        String readLine;
        while ((readLine = bufferedReader.readLine()) != null){
            result += readLine;
        }
        in.close();
        urlConn.disconnect();

        return result;
    }

    private static String generateGetUrl(String strUrl, HashMap<String, String> params){
        if (!strUrl.contains("?")){
            strUrl += "?";
        }

        int cnt = 0;
        for(Entry<String, String> entry : params.entrySet()){
        	if (cnt != 0){
            	strUrl += "&";
            }
            strUrl += (entry.getKey() + "=" + entry.getValue());
            cnt++;
        }

        return strUrl;
    }
}
