package com.lc.df.ftp.sync.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpsSessionFactory;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.stereotype.Component;

@Component
public class FTPConfiguration {


    @Value("${ftp.server.user.name}")
	private String user;
    @Value("${ftp.server.user.pwd}")
	private String pwd;
    @Value("${ftp.server.host.name}")
	private String host;
    @Value("${ftp.server.host.port:22}")
	private String port;
	
	@Bean
	@Qualifier("sftpSessionFactory")
	public DefaultSftpSessionFactory sftpSessionFactory(){
		DefaultSftpSessionFactory defaultSftpSessionFactory= new DefaultSftpSessionFactory();
		defaultSftpSessionFactory.setUser(user);
		defaultSftpSessionFactory.setPassword(pwd);
		defaultSftpSessionFactory.setHost(host);
		defaultSftpSessionFactory.setPort(Integer.parseInt(port));
		Properties config= new Properties();
		 config.put("PreferredAuthentications", "password");
		 config.put("StrictHostKeyChecking", "no");
		 config.put("PubkeyAuthentication", "no");
		defaultSftpSessionFactory.setSessionConfig(config);
		return defaultSftpSessionFactory;
	}
	
	@Bean
	@Qualifier("ftpSessionFactory")
	public DefaultFtpSessionFactory defaultSessionFactory(){
		DefaultFtpSessionFactory defaultFtpSessionFactory= new DefaultFtpSessionFactory();
		return defaultFtpSessionFactory;
	}
	
	
	@Bean
	@Qualifier("ftpsSessionFactory")
	public DefaultFtpsSessionFactory ftpsSessionFactory(){
		DefaultFtpsSessionFactory defaultFtpsSessionFactory= new DefaultFtpsSessionFactory();
		return defaultFtpsSessionFactory;
	}
	
}
