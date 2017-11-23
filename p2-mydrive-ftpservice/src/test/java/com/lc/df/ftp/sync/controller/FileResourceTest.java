/**
 * 
 */
package com.lc.df.ftp.sync.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
	
	@MockBean
	private FileResourceService fileResourceService;

	@Test
	public void testGetFileServiceExists() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/getFileList")).andExpect(status().isOk());

	}

}
