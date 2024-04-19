package com.techpixe.picnie.template.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techpixe.picnie.template.Entity.Template;
import com.techpixe.picnie.template.Entity.TextElement;
import com.techpixe.picnie.template.dto.ImageElementDTO;
import com.techpixe.picnie.template.dto.TemplateDto;
import com.techpixe.picnie.template.dto.TextElementDTO;
import com.techpixe.picnie.template.repository.TemplateRepository;
import com.techpixe.picnie.template.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TemplateRepository templateRepository;

	@Override
	public List<Template> getAllTemplates() {
		return templateRepository.findAll();
	}

	@Override
	public Template createTemplate(Template template) {
		List<TextElement> textElements = new ArrayList<>();
		for (TextElement textElement : template.getTextElements()) {
			textElement.setTemplate(template);
			textElement.setName(textElement.getName());
			textElement.setText(textElement.getText());
			textElement.setTextSize(textElement.getTextSize());
			textElement.setFontStyle(textElement.getFontStyle());
			textElement.setTextColor(textElement.getTextColor());
			textElement.setAngle(textElement.getAngle());
			textElement.setDestX(textElement.getDestX());
			textElement.setDestY(textElement.getDestY());
			textElement.setMaxLength(textElement.getMaxLength());
			textElement.setMaxLines(textElement.getMaxLines());
			textElement.setLetterSpacing(textElement.getLetterSpacing());
			textElement.setInputLineHeight(textElement.getInputLineHeight());
			textElement.setTextAlign(textElement.getTextAlign());
			// Add the text element to the list
			textElements.add(textElement);
			template.setTextElements(textElements);
		}

		// Save the template with its associated text elements
		return templateRepository.save(template);
	}

	@Override
	public String getById(long id) {
		Optional<Template> templateOptional = templateRepository.findById(id);
		if (templateOptional.isPresent()) {
			Template template = templateOptional.get();
			List<TextElement> textElements = template.getTextElements();
			if (!textElements.isEmpty()) {
				StringBuilder htmlBuilder = new StringBuilder();
				for (TextElement textElement : textElements) {
					htmlBuilder.append("<div style=\"");
					htmlBuilder.append("font-style: ").append(textElement.getFontStyle()).append("; ");
					htmlBuilder.append("font-size: ").append(textElement.getTextSize()).append("px; ");
					htmlBuilder.append("color: rgb(").append(textElement.getTextColor()).append(");");
					htmlBuilder.append("transform: rotate(").append(textElement.getAngle()).append("deg);");
					htmlBuilder.append("position: absolute; left: ").append(textElement.getDestX()).append("%; top: ")
							.append(textElement.getDestY()).append("%;");
					htmlBuilder.append("max-width: ").append(textElement.getMaxLength()).append("%;");
					htmlBuilder.append("line-height: ").append(textElement.getInputLineHeight()).append(";");
					htmlBuilder.append("letter-spacing: ").append(textElement.getLetterSpacing()).append("px;");
					htmlBuilder.append("text-align: ").append(textElement.getTextAlign()).append(";\">");
					htmlBuilder.append(textElement.getText());
					htmlBuilder.append("</div>");
				}
				return htmlBuilder.toString();
			}
			return "No text elements found for the template.";
		}
		return "Template not found.";
	}

	@Override
	public Template createTemplateWithElements(Template template, List<TextElementDTO> textElementsDTO,
			MultipartFile imageFile) {
		// TODO Auto-generated method stub
		return null;
	}

}
