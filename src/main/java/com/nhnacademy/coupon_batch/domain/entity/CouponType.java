package com.nhnacademy.coupon_batch.domain.entity;

import com.nhnacademy.coupon_batch.domain.CouponKind;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CouponType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long couponTypeId;
    @Column(name="couponType")
    private CouponKind couponKind;

}
