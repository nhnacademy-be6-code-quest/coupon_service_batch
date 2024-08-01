package com.nhnacademy.coupon_batch.scheduler;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CouponBatchScheduler {
    private static final String JOB_NAME = "couponUpdateJob";

    private final JobLauncher jobLauncher;
    private final Job couponUpdateJob;

    @Scheduled(cron = " 0 0 0 * * * ")
    public void runJob() {
        String time = LocalDateTime.now().toString();
        JobParameters jobParameters = new JobParametersBuilder()
            .addString("time", time)
            .toJobParameters();

        try {
            log.info("Starting job: {} at {}", JOB_NAME, time);
            jobLauncher.run(couponUpdateJob, jobParameters);
            log.info("Job: {} completed successfully", JOB_NAME);
        } catch (JobExecutionAlreadyRunningException e) {
            log.warn("Job: {} is already running", JOB_NAME);
        } catch (JobInstanceAlreadyCompleteException e) {
            log.warn("Job: {} with these parameters has already been completed", JOB_NAME);
        } catch (JobParametersInvalidException e) {
            log.error("Invalid job parameters for job: {}", JOB_NAME, e);
        } catch (JobRestartException e) {
            log.error("Error restarting job: {}", JOB_NAME, e);
        } catch (Exception e) {
            log.error("Unexpected error occurred while running job: {}", JOB_NAME, e);
        }
    }

}
