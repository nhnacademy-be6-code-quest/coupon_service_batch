package com.nhnacademy.coupon_batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CouponBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponBatchApplication.class, args);
    }

}
