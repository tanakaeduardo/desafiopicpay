package com.tanaka.desafiopicpay.desafiopicpay.dtos;

import java.math.BigDecimal;

import com.tanaka.desafiopicpay.desafiopicpay.domain.user.UserType;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
	

}
