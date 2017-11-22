/**
 * 
 */
package com.lc.df.ftp.sync.service;

import java.io.EOFException;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Logger;

import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import static org.hsqldb.HsqlDateTime.e;

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

	public Vector getFiles(String Path) {
        String SFTPHOST = "localhost";
        int SFTPPORT = 22;
        String SFTPUSER = "foo";
        String SFTPPASS = "pass";
        String SFTPWORKINGDIR = "/upload";

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;

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
                System.out.println(filelist.get(i).toString());
            }
            return filelist;



        } catch (JSchException e1) {
            e1.printStackTrace();
        } catch (SftpException e1) {
            e1.printStackTrace();
        }
        return null;
    }
    public boolean upload(MultipartFile file) throws IOException {

		try {
			defaultSftpSessionFactory.getSession().write(file.getInputStream(), remotePath + "/" + file.getOriginalFilename());

			return true;
		}
		catch (MultipartException m)
        {
            throw new MultipartException("File Missing or invalid :"+m.getMessage());
        }
		catch (IOException e) {
			//TODO log the exception

            throw new IllegalArgumentException("File Missing or invalid!!!" +e.getMessage());

		}
		catch(Exception e)
		{
            throw new IllegalArgumentException("File Missing or invalid!!!" +e.getMessage());

		}
    }

}
