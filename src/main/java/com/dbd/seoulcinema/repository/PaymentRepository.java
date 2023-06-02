package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,String> {
}
