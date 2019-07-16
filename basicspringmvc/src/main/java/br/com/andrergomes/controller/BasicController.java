package br.com.andrergomes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

	@GetMapping("/")
	public String init() {
		return "index";
	}
}
