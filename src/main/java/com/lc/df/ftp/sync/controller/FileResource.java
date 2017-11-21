package com.lc.df.ftp.sync.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lc.df.ftp.sync.service.FileResourceService;

@RestController
public class FileResource {

	@Autowired
	private FileResourceService fileResourceService;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

		if (file.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Uploaded file is empty");

		boolean result = fileResourceService.upload(file);

		if (result)
			return  ResponseEntity.status(HttpStatus.OK).body(file.getOriginalFilename() + " file uploaded successfully");

		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error while uploading the file");
	}
}
