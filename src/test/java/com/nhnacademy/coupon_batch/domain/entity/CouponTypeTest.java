package com.nhnacademy.coupon_batch.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nhnacademy.coupon_batch.domain.CouponKind;
import org.junit.jupiter.api.Test;

class CouponTypeTest {

    @Test
    void testCouponTypeEntityConstructor() {

        CouponType couponType = new CouponType(1L, CouponKind.BIRTHDAY);

        assertEquals(1L, couponType.getCouponTypeId());
        assertEquals(CouponKind.BIRTHDAY, couponType.getCouponKind());
    }
}
