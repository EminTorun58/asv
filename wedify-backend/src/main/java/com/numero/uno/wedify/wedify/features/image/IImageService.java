package com.numero.uno.wedify.wedify.features.image;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
	String getImage(String type, Long typeId);

	String uploadImageFacility(MultipartFile file, Long venueId);

	void removeImageFacility(String filename, Long venueId);
}
