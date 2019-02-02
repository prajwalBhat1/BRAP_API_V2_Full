package com.brap.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.brap.common.exception.UnableToUploadFileException;
import com.brap.schedule.request.UploadFileRequest;

@Component
public class FileUploaderUtil {

	@Autowired
	private PropertyReaderUtil readerUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploaderUtil.class);

	public String uploadFile(UploadFileRequest fileRequest) {
		StringBuilder status = new StringBuilder();
		File dir = new File(readerUtil.getUploadFilePath());
		if (fileRequest.getFiles().length == 0) {
			throw new UnableToUploadFileException("User has not selected any files ",
					"Please select the files to upload and try again !!");
		}
		for (MultipartFile file : fileRequest.getFiles()) {
			try {
				byte[] bytes = file.getBytes();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				StringBuilder fileName = new StringBuilder().append(fileRequest.getJobName()).append("_")
						.append(file.getOriginalFilename());
				File uploadFile = new File(dir.getAbsolutePath() + File.separator + fileName.toString());
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
				outputStream.write(bytes);
				outputStream.close();
				status.append("You successfully uploaded the file :  ").append(file.getOriginalFilename())
						.append(StringUtils.SPACE);
			} catch (IOException e) {
				LOGGER.info(String.format("unable to upload file : [%s] ", e.getMessage()));
				throw new UnableToUploadFileException("Unable to upload Files!!..",
						"Unable to upload Files . Please try after sometime !!");
			}
		}
		return status.toString();
	}

	public Map<String, MultipartFile> createMapForFileAndFileNames(MultipartFile[] files, String jobName) {
		Map<String, MultipartFile> fileMap = new HashMap<>();
		for (MultipartFile file : files) {
			StringBuilder fileName = new StringBuilder().append(jobName).append("_").append(file.getOriginalFilename());
			fileMap.put(fileName.toString(), file);
		}
		return fileMap;
	}
}
