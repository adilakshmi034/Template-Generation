package com.techpixe.picnie.template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.techpixe.picnie.template.Entity.Template;
import com.techpixe.picnie.template.service.TemplateService;

@RestController
@RequestMapping("/api/templates")

public class TemplateController {
	@Autowired
	private TemplateService templateService;


	@GetMapping
	public List<Template> getAllTemplates() {
		return templateService.getAllTemplates();
	}
	
	@PostMapping("/create")
	public ResponseEntity<Template> createTemplate(@RequestBody Template template) {
		templateService.createTemplate(template);
		return ResponseEntity.ok(template);

	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getTextElementHtmlByTemplateId(@PathVariable Long id) {
		String htmlMarkup = templateService.getById(id);
		if (htmlMarkup != null) {
			return ResponseEntity.ok(htmlMarkup);
		}
		return ResponseEntity.notFound().build();
	}

}
