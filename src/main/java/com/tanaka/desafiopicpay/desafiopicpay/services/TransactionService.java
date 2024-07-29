package com.tanaka.desafiopicpay.desafiopicpay.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tanaka.desafiopicpay.desafiopicpay.domain.transaction.Transaction;
import com.tanaka.desafiopicpay.desafiopicpay.domain.user.User;
import com.tanaka.desafiopicpay.desafiopicpay.dtos.TransactionDTO;
import com.tanaka.desafiopicpay.desafiopicpay.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private NoticifationService notificationService;
	
	public Transaction creatTransaction (TransactionDTO transaction) throws Exception {
		User sender = this.userService.findUserById(transaction.senderId());
		User reciver = this.userService.findUserById(transaction.reciverId());
		
		userService.validateTransaction(sender, transaction.value());
		
		boolean isAuthorizer = this.authorizeTransaction(sender, transaction.value()); 
		if(!isAuthorizer) {
			throw new Exception("Transacao nao autorizada");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(transaction.value());
		newTransaction.setSender(sender);
		newTransaction.setReciver(reciver);
		newTransaction.setTimestamp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(transaction.value()));
		reciver.setBalance(reciver.getBalance().add(transaction.value()));
		
		this.repository.save(newTransaction);
		this.userService.saveUser(sender);
		this.userService.saveUser(reciver);
		
		this.notificationService.sendNotification(sender, "Notificacao enviada com sucesso");
		
		this.notificationService.sendNotification(reciver, "Notificacao recebida com sucesso");
		
		return newTransaction;
	}
	
	public boolean authorizeTransaction(User sender,  BigDecimal value) {
		//ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
		
		if(true){//authorizationResponse.getStatusCode() == HttpStatus.OK) {
			String message =  "Autorizado";//authorizationResponse.getBody().getElement().toString();//("message");
			return "Autorizado".equalsIgnoreCase(message);
		} else return false;
	}
}
