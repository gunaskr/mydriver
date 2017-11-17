package com.lc.df.ftp.sync.task;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.stereotype.Component;

@Component
public class FTPTasklet implements Tasklet{
	
	private volatile boolean sync=true;
	
	@Value("${ftp.local.resource.path}")
    private String localPath;
    
    @Value("${ftp.remote.resource.path}")
    private String remotePath;

	private SftpInboundFileSynchronizer ftpInboundFileSynchronizer;
    
    
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		if(sync){
			 ftpInboundFileSynchronizer.synchronizeToLocalDirectory(new File(localPath));
			 
			 return RepeatStatus.FINISHED;
		}
		return RepeatStatus.CONTINUABLE;
	}
	
	@Bean																  				
	public SftpInboundFileSynchronizer sftpInfoundFileSync(SessionFactory sftpSessionFactory){
		ftpInboundFileSynchronizer = new SftpInboundFileSynchronizer(sftpSessionFactory);
		ftpInboundFileSynchronizer.setRemoteDirectory(remotePath);
		return ftpInboundFileSynchronizer;
	}


	/**
	 * @param localPath
	 */
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	/**
	 * 
	 * @param remotePath
	 */
	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	
	
}
