package com.tanaka.desafiopicpay.desafiopicpay.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanaka.desafiopicpay.desafiopicpay.domain.user.User;
import com.tanaka.desafiopicpay.desafiopicpay.domain.user.UserType;
import com.tanaka.desafiopicpay.desafiopicpay.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository reposetory;
	
	public void validateTransaction(User sender, BigDecimal amount) throws Exception {
		if(sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Usuario do ripo lojista sem permição para a transação");
		}
		
		if (sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo insuficiente");
		}
	}
	
	public User findUserById(Long id) throws Exception {
		return this.reposetory.findUserById(id).orElseThrow(() -> new Exception("Usuario nao encontrado"));
	}
	
	public void saveUser(User user) {
		this.reposetory.save(user);
	}
}