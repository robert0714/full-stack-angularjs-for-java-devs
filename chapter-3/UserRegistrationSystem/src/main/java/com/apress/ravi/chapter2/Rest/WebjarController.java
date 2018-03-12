//package com.apress.ravi.chapter2.Rest;
// 
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody; 
//import org.webjars.RequireJS;
//
//@Controller
//@RequestMapping("/webjars")
//public class WebjarController {
//	@ResponseBody
//	@RequestMapping(value = "/**/*.js", produces = "application/javascript")
//	public String webjarjs() {
//		return RequireJS.getSetupJavaScript("/webjars/");
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "/**/*.css", produces = "text/css")
//	public String webjarcss() {
//		return RequireJS.getSetupJavaScript("/webjars/");
//	}
//}
