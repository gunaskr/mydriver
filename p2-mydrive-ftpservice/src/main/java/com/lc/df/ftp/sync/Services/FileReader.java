package com.lc.df.ftp.sync.Services;

import com.jcraft.jsch.ChannelSftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class FileReader {

    private final Logger LOGGER= LoggerFactory.getLogger(this.getClass());
    @Autowired
    DefaultSftpSessionFactory defaultSftpSessionFactory;

    public String deleteFile(String fileName)
    {
        if(defaultSftpSessionFactory.getSession().isOpen())
        {
            try {
                    defaultSftpSessionFactory.getSession().remove(fileName);
                    LOGGER.info("Deleted file " + fileName);
                    return "Deleted Successfully";
            }
            catch(Exception e)
            {
                LOGGER.error(fileName+" file not Found");
                return "File Not Found";
            }
        }
        else
        {
            LOGGER.error("Internal Server Error");
            return "Connection not established";
        }
    }


}
