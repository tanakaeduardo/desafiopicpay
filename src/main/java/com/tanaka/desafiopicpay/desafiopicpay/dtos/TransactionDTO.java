package com.tanaka.desafiopicpay.desafiopicpay.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long reciverId) {

}
