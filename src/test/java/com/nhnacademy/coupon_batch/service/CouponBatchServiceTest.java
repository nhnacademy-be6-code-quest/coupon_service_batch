//package com.nhnacademy.coupon_batch.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.util.ReflectionTestUtils.setField;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.Optional;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//public class CouponBatchServiceTest {
//    @Test
//    void testRunMethod() {
//        Coupon expiredCoupon;
//        Coupon usedCoupon;
//        expiredCoupon = new Coupon();
//        setField(expiredCoupon,"couponId",1L);
//        setField(expiredCoupon,"expirationDate", LocalDate.now().minusDays(10));
//        setField(expiredCoupon,"status",Status.AVAILABLE);
//
//        usedCoupon = new Coupon();
//        setField(usedCoupon,"couponId",2L);
//        setField(usedCoupon,"expirationDate",LocalDate.now().minusDays(5));
//        setField(usedCoupon,"status",Status.USED);
//
//        // Mock repository behavior
//        when(couponRepository.findByExpirationDateBefore(any(LocalDate.class)))
//            .thenReturn(Arrays.asList(expiredCoupon, usedCoupon));
//        when(couponRepository.findById(expiredCoupon.getCouponId()))
//            .thenReturn(Optional.of(expiredCoupon));
//        when(couponRepository.findById(usedCoupon.getCouponId()))
//            .thenReturn(Optional.of(usedCoupon));
//
//        // Execute the scheduled task
//        schedulerServiceImpl.run();
//
//        // Verify the coupon statuses
//        assertEquals(Status.UNAVAILABLE, expiredCoupon.getStatus(), "Expired coupon should be UNAVAILABLE");
//        assertEquals(Status.USED, usedCoupon.getStatus(), "Used coupon should remain USED");
//
//        // Verify repository interactions
//        Mockito.verify(couponRepository).findByExpirationDateBefore(any(LocalDate.class));
//        Mockito.verify(couponRepository).saveAll(Arrays.asList(expiredCoupon, usedCoupon));
//    }
//
//}
