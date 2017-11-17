package com.lc.df.ftp.sync.task;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;

import com.lc.df.ftp.sync.task.FTPTasklet;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = FTPTasklet.class)
public class FTPTaskletTest {

	@InjectMocks
	FTPTasklet tasklet;
	@Mock
	private ChunkContext chunkContext;
	@Mock
	private StepContribution contribution;
	@Mock
	private SessionFactory sftpSessionFactory;
	
	@Mock
	Session session;
	
	@Before
	public void setUp() throws Exception {
		tasklet.setLocalPath("./");
		tasklet.setRemotePath("/");
	}

	@Test
	public void testExecute() throws Exception {

		tasklet.sftpInfoundFileSync(sftpSessionFactory);
		tasklet.execute(contribution, chunkContext);
	}

}
