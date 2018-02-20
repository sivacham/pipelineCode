package com.cicd.pipeline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cicd.pipeline.service.TemplateGenerationService;

@RestController
public class GeneratePipeline {
	
	@Autowired
	private TemplateGenerationService templateGenerationService;
	
	@GetMapping("/pipeline/generateTemplate")
	public String retrieveCoursesForStudent() {
		return templateGenerationService.generateTemplate();
	}

}
