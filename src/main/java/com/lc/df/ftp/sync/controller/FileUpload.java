package com.lc.df.ftp.sync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class FileUpload {


    @Autowired
    private DefaultSftpSessionFactory defaultSftpSessionFactory;

    @Value("${ftp.remote.resource.path}")
    private String remotePath;

    @RequestMapping("/uploadFile")
    public boolean fileUpload() {

        System.out.println("Data:::::::::" + defaultSftpSessionFactory.getSession().isOpen());
        String localFileFullName = "C:\\\\ftp_files\\\\pocketftp.png";
        System.out.println("File Picked");
        boolean result = false;
        try (InputStream input = new FileInputStream(new File(localFileFullName))) {

            //result = this.ftp.storeFile("/pub/examples/" + "image.png", input);
            defaultSftpSessionFactory.getSession().write(input, remotePath+ "/image.png");

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
