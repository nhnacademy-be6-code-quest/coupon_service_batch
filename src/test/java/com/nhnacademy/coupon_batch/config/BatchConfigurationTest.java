package com.nhnacademy.coupon_batch.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nhnacademy.coupon_batch.service.CouponBatchServiceImpl;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootTest(classes = BatchConfiguration.class)
@EnableBatchProcessing
@Import(BatchConfigurationTest.BatchTestConfig.class)
class BatchConfigurationTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private CouponBatchServiceImpl couponBatchService;

    @MockBean
    private JobRepository jobRepository;

    @Test
    void testBeansAreLoaded() {
        assertNotNull(context.getBean("couponUpdateJob", Job.class), "couponUpdateJob bean should be loaded in the context");
        assertNotNull(context.getBean("couponUpdateStep", Step.class), "couponUpdateJob bean should be loaded in the context");
        assertNotNull(context.getBean("couponUpdateTasklet", Tasklet.class), "couponUpdateJob bean should be loaded in the context");
    }

    @TestConfiguration
    static class BatchTestConfig {

        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        }

        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }
}
