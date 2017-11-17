package com.lc.df.ftp.sync.scheduler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import com.lc.df.ftp.sync.scheduler.FTPJobScheduler;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = FTPJobScheduler.class)
public class FTPJobSchedulerTest {
	@Mock
    JobLauncher jobLauncher;
	@InjectMocks
	FTPJobScheduler scheduler;
	@Mock
	Resource resource=Mockito.mock(Resource.class);
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRunJob() throws Exception{
		JobExecution execution = Mockito.mock(JobExecution.class);
		Mockito.when(jobLauncher.run(Mockito.anyObject(), Mockito.anyObject())).thenReturn(execution );
		scheduler.runJob();
	}

}
