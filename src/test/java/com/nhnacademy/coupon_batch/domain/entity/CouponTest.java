package com.nhnacademy.coupon_batch.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nhnacademy.coupon_batch.domain.Status;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class CouponTest {

    @Test
    void testCouponEntityConstructor() {

        CouponType couponType = new CouponType();
        CouponPolicy couponPolicy = new CouponPolicy();


        Coupon coupon = Coupon.builder()
            .clientId(1L)
            .couponType(couponType)
            .couponPolicy(couponPolicy)
            .expirationDate(LocalDate.now().plusDays(30))
            .status(Status.AVAILABLE)
            .build();

        assertEquals(1L, coupon.getClientId());
        assertEquals(couponType, coupon.getCouponType());
        assertEquals(couponPolicy, coupon.getCouponPolicy());
        assertNotNull(coupon.getIssuedDate());
        assertNotNull(coupon.getExpirationDate());
        assertEquals(Status.AVAILABLE, coupon.getStatus());
    }
}
