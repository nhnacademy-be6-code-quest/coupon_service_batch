package com.nhnacademy.coupon_batch.service;

public interface CouponBatchService {


    /**
     * 매일 12시 쿠폰의 만료시간이 현재일보다 작으면 상태를 변경
     *
     * @author jjeonmin
     */
    void updateExpirationCoupon();
}
