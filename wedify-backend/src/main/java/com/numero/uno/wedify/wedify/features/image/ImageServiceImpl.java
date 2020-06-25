package com.numero.uno.wedify.wedify.features.image;

import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.company.Company;
import com.numero.uno.wedify.wedify.features.facility.Facility;
import com.numero.uno.wedify.wedify.features.facility.FacilityRepository;
import com.numero.uno.wedify.wedify.features.venue.Venue;
import com.numero.uno.wedify.wedify.features.venue.VenueRepository;
import com.numero.uno.wedify.wedify.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ImageServiceImpl implements IImageService {
	private final FacilityRepository facilityRepository;
	final String BASE = "http://localhost:8080/";
	final String IMAGE_LOCATION = "src/main/resources/data/";

	@Autowired
	public ImageServiceImpl(FacilityRepository facilityRepository) {
		this.facilityRepository = facilityRepository;
	}

	public void removeImage(String filename, String type, Long typeId) {
		File file = new File(IMAGE_LOCATION + type + "/" + typeId + "/" + filename);
		if (!file.isFile()) {
			throw new RuntimeException("FacilityImage not found!");
		}

		if (!file.delete()) {
			throw new RuntimeException("Couldn't delete the image");
		}
	}

	@Override
	public void removeImageFacility(String filename, Long facilityId) {
		Long authenticatedCompanyId = getCurrentCompanyId();
		Optional<Facility> facility = facilityRepository.findById(facilityId);
		facility.orElseThrow(() -> new ResourceNotFoundException(Company.class.getSimpleName(), facilityId));
		if (authenticatedCompanyId == facility.get().getCompany().getId()) {
			removeImage(filename, "facility", facilityId);
		} else { throw new AccessDeniedException("You're not authorized!");}
	}


	@Override
	public String uploadImageFacility(MultipartFile file, Long facilityId) {
		Long authenticatedCompanyId = getCurrentCompanyId();
		Optional<Facility> facility = facilityRepository.findById(facilityId);
		facility.orElseThrow(() -> new ResourceNotFoundException(Company.class.getSimpleName(), facilityId));

		if (authenticatedCompanyId == facility.get().getCompany().getId()) {
			return uploadImage(file, "venue", facilityId);
		} else {
			throw new AccessDeniedException("You're not authorized!");
		}
	}


	protected Long getCurrentCompanyId(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Long authenticatedCompanyId = ((UserPrincipal) authentication.getPrincipal()).getId();
		return authenticatedCompanyId;
	}


	//Type could be; 'catering' or 'venue' or 'facility'
	public String uploadImage(MultipartFile file, String type, Long typeId) {
		String uploadFolder = IMAGE_LOCATION + type + "/" + typeId;

		//Determine new filename
		File folder = new File(uploadFolder + "/");
		String fileName = (folder.listFiles().length + 1) + "_" + file.getOriginalFilename();

		//Create folder if not exists
		new File(uploadFolder).mkdirs();

		if (file.isEmpty()) {
			return "Please image a file.";
		}
		try {
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();

			//18 591 : 1000 bytes = 18.6 kB
			double kB = (file.getSize() / 1000);
			Path path = Paths.get(uploadFolder + "/" + fileName);

			//Throw error if groter dan 800kB
			if (kB > 800) {
				throw new RuntimeException("File size must be less then 800kB.");
			}

			if (!file.getContentType().equals("image/jpeg")) {
				throw new RuntimeException("File type must be a jpg/jpeg format.");
			}

			Files.write(path, bytes);

			return uploadFolder + "/" + fileName;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}


	public String getImage(String type, Long typeId) {
		String location = BASE + type + "/" + typeId + "/";
		File folder = new File(IMAGE_LOCATION + type + "/" + typeId + "/");
		String images = "{images:[";
		/*Get files*/
		File[] files = folder.listFiles();
		if (files != null){
			for (File file : files) {
				if (file.isFile()) {
					images += "\"" + location + file.getName() + "\",";
				}
			}
		}

		images += "]}";
		return images;
	}

}
