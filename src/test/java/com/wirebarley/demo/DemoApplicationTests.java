package com.wirebarley.demo;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wirebarley.api.ExchangeApiService;
import com.wirebarley.api.ExchangeApiVO;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	ExchangeApiService exchangeApiService;	// 환율정보 api Service 
	
	@Test
	public void contextLoads() {
		
		ExchangeApiVO vo = new ExchangeApiVO();
		// 테스트용 하드코딩 
		vo.setUrl("http://api.currencylayer.com/live?access_key=e526110ddf705b3d2ebdd3ba2757f4f3&format=1");
		
		Map<String, String> exchangeMap = exchangeApiService.getExchangeRate(vo);
		
		System.out.println("환율정보 조회 성공 여부 : " + exchangeMap.get("success"));
		System.out.println("환율정보 중 KRW : " + exchangeMap.get("USDKRW"));
	}

}
