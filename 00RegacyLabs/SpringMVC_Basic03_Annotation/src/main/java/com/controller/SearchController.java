package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

		/*
		 1. 전통적인 방식 (request)
		 2. DTO (insert, update)
		 3. parameter > list.do?id=7788  > search(String id)
		 4. @RequestParam >> parameter 제어 가능(default)
		  
		  
		 */
	@RequestMapping("/search/external.do")
	public ModelAndView searchExternal(
			@RequestParam(value="query", defaultValue = "kosa") String query,
			@RequestParam(value="p",     defaultValue = "10") int p)
	{
		System.out.println("param query : " + query);
		System.out.println("param p : " + p);
		
		return new ModelAndView("search/external");
	}
}

