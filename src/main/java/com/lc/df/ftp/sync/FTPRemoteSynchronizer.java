package com.lc.df.ftp.sync;

import com.lc.df.ftp.sync.controller.FileUpload;
import com.sun.javaws.exceptions.ExitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
public class FTPRemoteSynchronizer {

	public static void main(String[] args) {
		SpringApplication.run(FTPRemoteSynchronizer.class, args);
	}
}
