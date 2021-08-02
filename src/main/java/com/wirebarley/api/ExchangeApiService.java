package com.wirebarley.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class ExchangeApiService {

	/*
	 * desciription 환율 정보 Http 통신
	 * */
	public Map<String, String> getExchangeRate(ExchangeApiVO vo) {
		
		// 클라이언트에 응답할 Map 선언 
		Map<String, String> exchangeMap = new HashMap<String, String>();
		
		URL url = null;
	    HttpURLConnection conn = null;
	    String jsonData = "";
	    BufferedReader br = null;
	    StringBuffer sb = null;
	    String returnText = "";
	 
	    try {
	    	// url 세팅
	        url = new URL(vo.getUrl());
	 
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestMethod("GET");
	        conn.connect();
	 
	        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        sb = new StringBuffer();
	 
	        // return String 문자열 append
	        while ((jsonData = br.readLine()) != null) {
	            sb.append(jsonData);
	        }
	 
	        // return 문자열 완성
	        returnText = sb.toString();
	        
	        
	        // json String -> json Object
	        JSONParser parser = new JSONParser();
	        Object obj = new Object();
	        
	        try {
	        	// jsonString 문자열 JSON Parsing
				obj = parser.parse(returnText);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        JSONObject jsonObj = (JSONObject) obj;	// JSON Parsing한 Object JSON Object로 Parsing
	        JSONObject quotesObj = (JSONObject) jsonObj.get("quotes");	// 각국의 환율정보 Map Json Object set
	        Boolean successYn = (Boolean) jsonObj.get("success");		// api 호출 성공여부 Json Object set
	        
	        // api 호출 성공 시 환율정보 세팅
	        if (successYn) {
	        	exchangeMap.put("success", "Y");
	        	
	           // 환율정보 
	 	       // KRW/USD 환율, JPY/USD 환율, PHP/USD 환율 적재 -> 소수점 2자리까지
	 	       exchangeMap.put("USDKRW", String.format("%.2f", quotesObj.get("USDKRW")));
	 	       exchangeMap.put("USDJPY", String.format("%.2f", quotesObj.get("USDJPY")));
	 	       exchangeMap.put("USDPHP", String.format("%.2f", quotesObj.get("USDPHP")));
	 	       
	        } else {
	        	exchangeMap.put("success", "N");
	        }
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	        
	    } finally {
	    	
	        try {
	            if (br != null) br.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		return exchangeMap;
	}

}
