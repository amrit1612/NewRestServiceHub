package com.PaymentService.paymentService.Repository;

import com.PaymentService.paymentService.Entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransationDatailRepository extends JpaRepository<TransactionDetails,Long> {
    Optional<TransactionDetails>findByOrderId(long orderId);
}
