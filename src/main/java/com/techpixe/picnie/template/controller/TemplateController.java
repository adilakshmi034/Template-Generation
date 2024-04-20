package com.techpixe.picnie.template.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techpixe.picnie.template.Entity.Template;
import com.techpixe.picnie.template.Entity.TextElement;
import com.techpixe.picnie.template.dto.TextElementDTO;
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
	public ResponseEntity<Template> createTemplateWithElements(@RequestParam("imageFile") MultipartFile imageFile,
			@RequestParam("templateName") String templateName, @RequestParam("type") String type,
			@RequestBody List<TextElement> textElements,@RequestParam(required=false) String imageName,@RequestParam() String imageType) throws IOException {
		// Create a new Template object
		Template template = new Template();
		template.setTemplateName(templateName);
		template.setType(type);

		// Call the service method to create the template with elements
		Template createdTemplate = templateService.createTemplateWithElements(template, textElements, imageFile,imageName,imageType);

		// Return the created template in the response
		return ResponseEntity.ok(createdTemplate);
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
