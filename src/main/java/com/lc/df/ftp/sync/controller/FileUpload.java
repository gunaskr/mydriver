package com.lc.df.ftp.sync.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.logging.Logger;

@RestController
public class FileUpload {


    @Autowired
    private DefaultSftpSessionFactory defaultSftpSessionFactory;

    @Value("${ftp.remote.resource.path}")
    private String remotePath;
    private final org.slf4j.Logger LOGGER= LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/uploadFile")
    public boolean fileUpload() {

        LOGGER.info("fileUpload method called");
        System.out.println("Data:::::::::" + defaultSftpSessionFactory.getSession().isOpen());
        String localFileFullName = "C:\\\\ftp_files\\\\pocketftp.png";
        System.out.println("File Picked");
        boolean result = false;
        try (InputStream input = new FileInputStream(new File(localFileFullName))) {

            //result = this.ftp.storeFile("/pub/examples/" + "image.png", input);
            defaultSftpSessionFactory.getSession().write(input, remotePath+ "/image.png");
            LOGGER.info("File Uploaded Successfully");
            return true;
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(String.valueOf(e.getStackTrace()));
        }
        catch (Exception e)
        {
            LOGGER.error(String.valueOf(e.getStackTrace()));
        }
        return false;
    }
}
