package com.wirebarley.demo;

import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.wirebarley.api.ExchangeApiService;
import com.wirebarley.api.ExchangeApiVO;

@ExtendWith(SpringExtension.class)	// 스프링부트와 junit5 연결 
//@SpringBootTest
@WebMvcTest
class DemoApplicationTests {

	@Autowired
	ExchangeApiService exchangeApiService;	// 환율정보 api Service 
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void index_return() throws Exception {
		String index = "index";
		
		mockMvc.perform(get("/"))
		.andExpect(status().isOk());	// index root context 호출 200 떨어지는지 확
//		.andExpect(content().string(index));
	}
	
	@Test
	public void contextLoads() {
		
		ExchangeApiVO vo = new ExchangeApiVO();
		// 테스트용 하드코딩 
		vo.setUrl("http://api.currencylayer.com/live?access_key=e526110ddf705b3d2ebdd3ba2757f4f3&format=1");
		
		Map<String, String> exchangeMap = exchangeApiService.getExchangeRate(vo);	// API Service 정상 조회 되는지 확
		
		System.out.println("환율정보 조회 성공 여부 : " + exchangeMap.get("success"));
		System.out.println("환율정보 중 KRW : " + exchangeMap.get("USDKRW"));
	}

}
