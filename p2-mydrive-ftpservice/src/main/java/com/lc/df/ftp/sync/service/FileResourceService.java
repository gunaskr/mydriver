/**
 * 
 */
package com.lc.df.ftp.sync.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author vjalihal
 *
 */

@Service
public class FileResourceService {

	@Autowired
	private DefaultSftpSessionFactory defaultSftpSessionFactory;

	@Value("${ftp.remote.resource.path}")
	private String remotePath;

	public boolean upload(MultipartFile file) {

		try {
			defaultSftpSessionFactory.getSession().write(file.getInputStream(), remotePath + "/" + file.getOriginalFilename());
			return true;
		} catch (IOException e) {
			//TODO log the exception
			e.printStackTrace();
		}
		return false;
	}

}
