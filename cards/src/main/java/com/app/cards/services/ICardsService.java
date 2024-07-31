package com.app.cards.services;

import com.app.cards.dtos.CardsAndLoansDetailsDto;
import com.app.cards.dtos.CardsDto;

public interface ICardsService {

    String createCard(String  mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    Boolean updateCard(CardsDto cardsDto);

    Boolean deleteCard(String mobileNumber);

	CardsAndLoansDetailsDto fetchCustomerCardsAndLoansDetails(String mobileNumber);
}
