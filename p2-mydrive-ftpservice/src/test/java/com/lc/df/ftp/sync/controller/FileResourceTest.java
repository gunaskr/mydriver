/**
 * 
 */
package com.lc.df.ftp.sync.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.df.ftp.sync.service.FileResourceService;

/**
 * @author vjalihal
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FileResource.class)
public class FileResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	private FileResourceService fileResourceService;

	List<String> fileList = Arrays.asList("File1.txt","File2.txt");
	
	@Test
	public void testGetFileServiceExists() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/getFileList")).andExpect(status().isOk());

	}
	
	@Test
	public void testGetFileServiceReturnsListOfFiles() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/getFileList/FolderA/FolderB"))
		.andExpect(status().isOk())
		.andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList("file1.txt","file2.txt"))));
	}

}
