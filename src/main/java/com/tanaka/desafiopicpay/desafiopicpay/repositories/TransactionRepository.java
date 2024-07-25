package com.tanaka.desafiopicpay.desafiopicpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanaka.desafiopicpay.desafiopicpay.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
