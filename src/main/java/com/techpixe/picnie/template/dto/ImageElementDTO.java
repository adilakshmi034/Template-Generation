package com.techpixe.picnie.template.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageElementDTO {
	private String name;
	private String type;
    private String imageUrl;

}
