package com.tanaka.desafiopicpay.desafiopicpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tanaka.desafiopicpay.desafiopicpay.domain.user.User;
import com.tanaka.desafiopicpay.desafiopicpay.dtos.NotificationDTO;

@Service
public class NoticifationService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void sendNotification(User user, String message) throws Exception {
		String email = user.getEmail();
		
		NotificationDTO notificationRequest = new NotificationDTO(email, message);
		
		ResponseEntity<String> notificationResponse = restTemplate.postForEntity( "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", notificationRequest, String.class);
		
		if(!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
			System.out.println("Erro ao enviar notificacao ");

			throw new Exception("Servico de notificacao fora do ar");
		}
	}

}

