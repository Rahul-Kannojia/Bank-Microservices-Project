package com.app.accounts.feignclients;

import org.springframework.stereotype.Component;

import com.app.accounts.dtos.CardsDto;

@Component
public class CardsFallback implements CardsFeignClient {

	@Override
	public CardsDto fetchCardDetails(String mobileNumber) {
		CardsDto cardsDto = new CardsDto();
		cardsDto.setStatus("Please try again after sometime");
		return cardsDto;
	}

}
