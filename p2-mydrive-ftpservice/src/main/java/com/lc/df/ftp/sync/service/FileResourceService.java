/**
 * 
 */
package com.lc.df.ftp.sync.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.*;
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

	public static List<String> getFiles(String uri) throws JSchException, SftpException {

        String SFTPHOST = "localhost";
        int SFTPPORT = 22;
        String SFTPUSER = "foo";
        String SFTPPASS = "pass";
        String SFTPWORKINGDIR = "/upload"+(uri.substring(uri.indexOf('/',uri.indexOf("/")+1)));

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        List<String> list= new ArrayList<>();


        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);
            Vector filelist = channelSftp.ls(SFTPWORKINGDIR);
            for (int i = 0; i < filelist.size(); i++) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) filelist.get(i);
                System.out.println(entry.getFilename());

                list.add(entry.getFilename());
                list.remove(".");
                list.remove("..");

            }



        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
