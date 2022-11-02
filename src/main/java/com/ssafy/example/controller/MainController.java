package com.ssafy.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping
public class MainController {

	@GetMapping(value = {"/", "index"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/sign_up")
	public String signUp() {
		return "sign_up";
	}
	
	@GetMapping("/info")
	public String info() {
		return "info";
	}
	
	@GetMapping("/search_apt")
	public String searchApt() {
		return "search_apt";
	}
	
	@GetMapping("/best_path")
	public String bestPath() {
		return "best_path";
	}
}
