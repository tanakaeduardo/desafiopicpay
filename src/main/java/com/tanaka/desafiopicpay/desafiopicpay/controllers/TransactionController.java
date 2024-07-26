package com.tanaka.desafiopicpay.desafiopicpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanaka.desafiopicpay.desafiopicpay.domain.transaction.Transaction;
import com.tanaka.desafiopicpay.desafiopicpay.dtos.TransactionDTO;
import com.tanaka.desafiopicpay.desafiopicpay.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception{
		Transaction newTransaction = this.transactionService.creatTransaction(transaction);
		return new ResponseEntity<Transaction>(newTransaction, HttpStatus.OK);
	}

}
