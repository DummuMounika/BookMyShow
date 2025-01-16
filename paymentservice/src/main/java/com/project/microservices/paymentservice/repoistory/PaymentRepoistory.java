package com.project.microservices.paymentservice.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.microservices.paymentservice.entity.PaymentEntity;

@Repository
public interface PaymentRepoistory extends JpaRepository<PaymentEntity, Integer> {
	
	

}
