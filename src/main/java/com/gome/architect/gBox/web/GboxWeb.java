package com.gome.architect.gBox.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gome.architect.gBox.videotools.Convertor;

@Controller
public class GboxWeb {



	@RequestMapping("/home.do")
	public String homeMapping(){
		return "home";
	}
	
	@RequestMapping("/link.do")
	public String linkTypeHandler(HttpServletRequest re,ModelMap m){

		return "typelist";
	}
	
	
	
	@RequestMapping(value="/list.do",method=RequestMethod.GET)
	public String videoListMapping(HttpServletRequest request,ModelMap m,Integer pageSize,Integer pageNum){
		


		return "recordMsg";
	}
	

	


	
}
