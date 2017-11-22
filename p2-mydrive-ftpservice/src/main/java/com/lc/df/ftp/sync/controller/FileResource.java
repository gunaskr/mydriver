package com.lc.df.ftp.sync.controller;

import com.lc.df.ftp.sync.service.FileResourceService;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


@RestController
public class FileResource {

    Vector fileList;

	@Autowired
	private FileResourceService fileResourceService;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

		if (file.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Uploaded file is empty");

		boolean result = fileResourceService.upload(file);

		if (result)
			return  ResponseEntity.status(HttpStatus.OK).body(file.getOriginalFilename() + " file uploaded successfully");

		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error while uploading the file");
	}

	@RequestMapping(value="/getFiles", method = RequestMethod.POST)
	public JSONArray getFiles(@RequestParam("path") String path) throws JSONException {

        /*if (path == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Enter Working Directory and try again");
        }*/

        List<String> files= new ArrayList<String>();

            fileList= fileResourceService.getFiles(path);

        JSONObject jsonObject;
        JSONArray jsonArray= new JSONArray();

        for (int i = 0; i < fileList.size(); i++) {

          jsonObject= new JSONObject(fileList.get(i).toString());

          jsonArray.put(jsonObject);

            files.add(fileList  .get(i).toString());

             }

            return jsonArray;

    }
}
