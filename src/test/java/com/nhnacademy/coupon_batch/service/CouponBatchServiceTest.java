package com.nhnacademy.coupon_batch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.nhnacademy.coupon_batch.domain.Status;
import com.nhnacademy.coupon_batch.domain.entity.Coupon;
import com.nhnacademy.coupon_batch.repository.CouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CouponBatchServiceTest {

    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponBatchServiceImpl couponBatchService;


    @Test
     void testUpdateExpirationCoupon_withUnexpiredCoupons() {
        // Given
        LocalDate now = LocalDate.now();
        Coupon coupon1 = Coupon.builder()
            .expirationDate(now.minusDays(1))
            .status(Status.AVAILABLE)
            .clientId(1L)
            .build();

        Coupon coupon2 = Coupon.builder()
            .status(Status.USED)
            .clientId(2L)
            .expirationDate(now.minusDays(2))
            .build(); // Already used, should not change
        List<Coupon> coupons = Arrays.asList(coupon1, coupon2);

        when(couponRepository.findByExpirationDateBefore(now)).thenReturn(coupons);

        // When
        couponBatchService.updateExpirationCoupon();

        // Then
        assertEquals(Status.UNAVAILABLE, coupon1.getStatus());
        assertEquals(Status.USED, coupon2.getStatus()); // Status should remain USED

        verify(couponRepository, times(1)).findByExpirationDateBefore(now);
        verify(couponRepository, times(1)).saveAll(coupons);
    }

    @Test
    void testUpdateExpirationCoupon_noCouponsToUpdate() {
        // Given
        LocalDate now = LocalDate.now();
        List<Coupon> coupons = Arrays.asList(); // No expired coupons

        when(couponRepository.findByExpirationDateBefore(now)).thenReturn(coupons);

        // When
        couponBatchService.updateExpirationCoupon();

        // Then
        verify(couponRepository, times(1)).findByExpirationDateBefore(now);
        verify(couponRepository, times(1)).saveAll(coupons); // saveAll should be called even with empty list
    }
}
