package com.nhnacademy.coupon_batch.scheduler;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

@ExtendWith(MockitoExtension.class)
class CouponBatchSchedulerTest {

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private Job couponUpdateJob;

    @InjectMocks
    private CouponBatchScheduler couponBatchScheduler;

    @Test
     void testRunJob_success() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
            .addString("time", LocalDateTime.now().toString())
            .toJobParameters();

        when(jobLauncher.run(eq(couponUpdateJob), any(JobParameters.class)))
            .thenReturn(mock(JobExecution.class));

        couponBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(couponUpdateJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_alreadyRunning() throws Exception {
        doThrow(new JobExecutionAlreadyRunningException("Job already running"))
            .when(jobLauncher).run(eq(couponUpdateJob), any(JobParameters.class));

        couponBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(couponUpdateJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_alreadyComplete() throws Exception {
        doThrow(new JobInstanceAlreadyCompleteException("Job already complete"))
            .when(jobLauncher).run(eq(couponUpdateJob), any(JobParameters.class));

        couponBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(couponUpdateJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_invalidParameters() throws Exception {
        doThrow(new JobParametersInvalidException("Invalid parameters"))
            .when(jobLauncher).run(eq(couponUpdateJob), any(JobParameters.class));

        couponBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(couponUpdateJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_restartException() throws Exception {
        doThrow(new JobRestartException("Job restart exception"))
            .when(jobLauncher).run(eq(couponUpdateJob), any(JobParameters.class));

        couponBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(couponUpdateJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_unexpectedException() throws Exception {
        doThrow(new RuntimeException("Unexpected exception"))
            .when(jobLauncher).run(eq(couponUpdateJob), any(JobParameters.class));

        couponBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(couponUpdateJob), any(JobParameters.class));
    }
}
