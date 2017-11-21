package com.lc.df.ftp.sync.controller;

import com.lc.df.ftp.sync.Services.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;

@RestController
public class FileResources {


    @Autowired
    private DefaultSftpSessionFactory defaultSftpSessionFactory;

    @Autowired
    private FileReader fileReader;

    @Value("${ftp.remote.resource.path}")
    private String remotePath;


    private final Logger LOGGER= LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/uploadFile")
    public boolean fileUpload() {
        System.out.println(remotePath);
        LOGGER.info("fileUpload method called");
        System.out.println("Data:::::::::" + defaultSftpSessionFactory.getSession().isOpen());
        String localFileFullName = "C:\\\\ftp_files\\\\pocketftp.png";
        System.out.println("File Picked");
        boolean result = false;
        try (InputStream input = new FileInputStream(new File(localFileFullName))) {

            //result = this.ftp.storeFile("/pub/examples/" + "image.png", input);
            defaultSftpSessionFactory.getSession().write(input, remotePath + "/image.png");
            LOGGER.info("File Uploaded Successfully");
            return true;
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(String.valueOf(e.getStackTrace()));
        } catch (Exception e) {
            LOGGER.error(String.valueOf(e.getStackTrace()));
        }
        return false;
    }

    @RequestMapping(value="/deleteFile/**", method= RequestMethod.DELETE)
    public ResponseEntity<String> deletfile(HttpServletRequest request)
    {
        String uri=request.getRequestURI();
        String status=fileReader.deleteFile(remotePath+uri.substring(uri.indexOf('/',uri.indexOf("/")+1)));
        if(status.equals("Connection Not established"))
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        else if(status.equals("Deleted Successfully"))
        {
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(status);
    }
}
