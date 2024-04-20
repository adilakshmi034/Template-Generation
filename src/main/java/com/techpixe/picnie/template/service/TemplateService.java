package com.techpixe.picnie.template.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techpixe.picnie.template.Entity.Template;
import com.techpixe.picnie.template.Entity.TextElement;
import com.techpixe.picnie.template.dto.TemplateDto;
import com.techpixe.picnie.template.dto.TextElementDTO;

public interface TemplateService {

	//Template createTemplate(Template template);

	List<Template> getAllTemplates();

	String getById(long id);

	Template createTemplateWithElements(Template template, List<TextElement> textElements,
			MultipartFile imageFile,String imageName,String imageType);

	// Template createTemplate(Template template);

}
