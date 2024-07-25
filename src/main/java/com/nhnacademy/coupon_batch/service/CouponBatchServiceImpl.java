package com.nhnacademy.coupon_batch.service;

import com.nhnacademy.coupon_batch.domain.Status;
import com.nhnacademy.coupon_batch.domain.entity.Coupon;
import com.nhnacademy.coupon_batch.repository.CouponRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponBatchServiceImpl implements CouponBatchService {

    private final CouponRepository couponRepository;

    @Override
    public void updateExpirationCoupon() {
        LocalDate now = LocalDate.now();
        List<Coupon> coupons = couponRepository.findByExpirationDateBefore(now);
        coupons.forEach((coupon -> {
            if (coupon.getStatus() != Status.USED) {
                coupon.setStatus(Status.UNAVAILABLE);
            }
        }));

        couponRepository.saveAll(coupons);
    }
}

