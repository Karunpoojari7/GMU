package com.gmu.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leads/health")
public class HealthController {
	
	@GetMapping("/info")
	public String helthInfo()
	{
		return "Lead Service";
	}
	

}
