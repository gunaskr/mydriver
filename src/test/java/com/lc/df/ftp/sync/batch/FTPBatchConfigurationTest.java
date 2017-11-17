package com.lc.df.ftp.sync.batch;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.boot.test.context.SpringBootTest;

import com.lc.df.ftp.sync.batch.FTPBatchConfiguration;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = FTPBatchConfiguration.class)
public class FTPBatchConfigurationTest {

	
	@Mock
	public JobBuilderFactory jobBuilderFactory;

	@Mock
	public StepBuilderFactory stepBuilderFactory;
	
	@InjectMocks
	FTPBatchConfiguration config;
	@Mock
	private JobBuilder jobBuilder;
	@Mock
	private JobFlowBuilder jobFlowBuilder;
	@Mock
	private StepBuilder stepBuilder;
	@Mock
	private SimpleStepBuilder<Object, Object> simpleStepBuilder;
	@Mock
	private FlowJobBuilder flowBuilder;
	@Mock
	private Job job;
	@Mock
	private Tasklet tasklet;
	@Mock
	private TaskletStepBuilder taskletStepBuilder;
	@Mock
	private TaskletStep taskletStep;
	
	@Test
	public void testFtpSycJob() {
		Mockito.when(jobBuilderFactory.get(Mockito.anyString())).thenReturn(jobBuilder);
		Mockito.when(jobBuilder.incrementer(Mockito.anyObject())).thenReturn(jobBuilder);
		Mockito.when(jobBuilder.listener(Mockito.anyObject())).thenReturn(jobBuilder);
		Mockito.when(jobBuilder.flow(Mockito.anyObject())).thenReturn(jobFlowBuilder);
		Mockito.when(stepBuilderFactory.get(Mockito.anyString())).thenReturn(stepBuilder);
		Mockito.when(stepBuilder.tasklet(Mockito.anyObject())).thenReturn(taskletStepBuilder);
		Mockito.when(taskletStepBuilder.build()).thenReturn(taskletStep);
		Mockito.when(jobFlowBuilder.end()).thenReturn(flowBuilder);
		Mockito.when(flowBuilder.build()).thenReturn(job);
		Assert.assertEquals(job,config.ftpSycJob());
	}

}
