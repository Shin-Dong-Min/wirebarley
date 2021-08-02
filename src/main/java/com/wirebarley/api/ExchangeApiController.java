package com.wirebarley.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="ExchangeApiController")
@RequestMapping("/api")
public class ExchangeApiController {

	@Autowired
	ExchangeApiService exchangeApiService;
	
	/*
	 * description : 환율정보 Api Controller 
	 * */
	@GetMapping("/getExchangeRate.ajax")
	public Map<String, String> getExchangeRate(ExchangeApiVO vo) {
		
		// 환율 api service class
		Map<String, String> exchangeMap = exchangeApiService.getExchangeRate(vo);
		
		return exchangeMap;
	}
	
	
}
