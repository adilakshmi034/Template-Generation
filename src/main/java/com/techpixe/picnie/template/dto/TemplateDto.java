package com.techpixe.picnie.template.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class TemplateDto {
	 private String templateName;
	    private String type;
	    private List<TextElementDTO> textElements;
	    private List<ImageElementDTO> imageElements;
	

}
