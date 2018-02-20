package com.cicd.pipeline.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.groovy.control.CompilationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.cicd.pipeline.model.PipelineModel;
import com.cicd.pipeline.service.TemplateGenerationService;

import groovy.lang.Writable;
import groovy.text.GStringTemplateEngine;
import groovy.text.Template;
import groovy.text.TemplateEngine;

@Service
public class TemplateGenerationServiceImpl implements TemplateGenerationService {
	
	@Autowired
    ResourceLoader resourceLoader;

	@Override
	public String generateTemplate() {
		
		String status = null;
		final TemplateEngine engine = new GStringTemplateEngine();
		
		Resource resource = resourceLoader.getResource("classpath:JenkinsFile_template.gtpl");
        try {
        	File templateFile = resource.getFile();
        	final Template template = engine.createTemplate(templateFile);
        	PipelineModel pipelineModel = new PipelineModel();
        	
        	pipelineModel.setBranch("\'master\'");
        	pipelineModel.setDevelopmentServer("\'dev-myproject.mycompany.com\'");
        	pipelineModel.setGitCredentials("\'d870229@sivacham\'");
        	pipelineModel.setProductionServer("\'production-myproject.mycompany.com\'");
        	pipelineModel.setScmUrl("\'ssh://git@myScmServer.com/repos/myRepo.git\'");
        	pipelineModel.setServerPort("\'8080\'");
        	pipelineModel.setStagingServer("\'staging-myproject.mycompany.com\'");
        	
        	final Map<String, Object> binding = new HashMap<String, Object>();
            binding.put("pipeline", pipelineModel);
            
            // Bind Map to template.
            final Writable out = template.make(binding);
             
            // We use a Writer object, can be any Writer e.g. FileWriter.
            final StringWriter writer = new StringWriter();
            out.writeTo(writer);
            System.out.println(writer.toString());
            status = "SUCCESS";
            
			
		} catch (IOException | CompilationFailedException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

}
