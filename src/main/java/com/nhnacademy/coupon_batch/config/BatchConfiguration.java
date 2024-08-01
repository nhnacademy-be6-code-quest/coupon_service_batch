package com.nhnacademy.coupon_batch.config;

import com.nhnacademy.coupon_batch.service.CouponBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final CouponBatchService couponBatchService;
    @Bean
    public Job couponUpdateJob() {
        return new JobBuilder("couponUpdateJob", jobRepository)
            .start(couponUpdateStep())
            .build();
    }

    @Bean
    public Step couponUpdateStep() {
        return new StepBuilder("couponUpdateJob", jobRepository)
            .tasklet(couponUpdateTasklet(), transactionManager)
            .build();
    }

    @Bean
    public Tasklet couponUpdateTasklet() {
        return ((contribution, chunkContext) -> {
            couponBatchService.updateExpirationCoupon();
            return RepeatStatus.FINISHED;
        });
    }
}