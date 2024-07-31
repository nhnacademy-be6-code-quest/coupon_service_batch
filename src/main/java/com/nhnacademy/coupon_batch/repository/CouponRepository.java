package com.nhnacademy.coupon_batch.repository;


import com.nhnacademy.coupon_batch.domain.entity.Coupon;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findByExpirationDateBefore(LocalDate expirationDate);

}
