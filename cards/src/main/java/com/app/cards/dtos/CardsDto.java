package com.app.cards.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CardsDto {
	private String mobileNumber;
	private String cardNumber;
	private String cardType;
	private int totalLimit;
	private int amountUsed;
	private int availableAmount;
}
