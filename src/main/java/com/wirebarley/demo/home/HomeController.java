package com.wirebarley.demo.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	/*
	 * root context 화면 이
	 * */
	@RequestMapping("/")
	public String index() {	
		return "index";
	}
}
