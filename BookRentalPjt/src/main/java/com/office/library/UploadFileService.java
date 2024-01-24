package com.office.library;

import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadFileService {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileService.class);
	public String upload(MultipartFile file) {
		boolean result = false;

		// File 저장
		String fileOriName = file.getOriginalFilename();
		String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
		String uploadDir = "C:\\library\\upload\\";

		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replaceAll("-", "");

		File saveFile = new File(uploadDir + "\\" + uniqueName + fileExtension);

		if (!saveFile.exists())
			saveFile.mkdirs();

		try {
			file.transferTo(saveFile);
			result = true;

		} catch (Exception e) {
			e.printStackTrace();

		}

		if (result) {
			logger.info("FILE UPLOAD SUCCESS!!");
			return uniqueName + fileExtension;

		} else {
			logger.info("FILE UPLOAD FAIL!!");
			return null;

		}
	}

}
