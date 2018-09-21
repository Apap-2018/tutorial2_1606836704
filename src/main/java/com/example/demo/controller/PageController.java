package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}
	
	@RequestMapping("/challenge")
	public String challenge(@RequestParam(value = "name") String name, Model model) {
		model.addAttribute("name", name);
		return "challenge";
	}
	
//	@RequestMapping("/challenge/{name}")
//	public String challengePath(@PathVariable String name, Model model) {
//		model.addAttribute("name", name);
//		return "challenge";
//	}
	
	@RequestMapping(value = {"/challenge", "challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		}else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
	}
	
	
	@RequestMapping("/generator")
	public String viralGenerator(@RequestParam(value="a", required = false, defaultValue = "0") String strA, @RequestParam(value="b", required = false, defaultValue = "0") String strB, Model model) {
		String hm = "h";
		int a = Integer.parseInt(strA);
		int b = Integer.parseInt(strB);
		
		if(a==0) hm +="m";
		else {
			for (int j=0; j < a; j++) {
				hm+="m";
			}
		}
		String finalH = hm;
		if (b>1) {
			for (int i=0; i < b-1; i++) {
				finalH += " "+hm;
			}
		}
		
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		model.addAttribute("h", finalH);
		return "generator";
	}
}
