package com.app.cards.services.impl;

import com.app.cards.dtos.CardsAndLoansDetailsDto;
import com.app.cards.dtos.CardsDto;
import com.app.cards.dtos.LoansDto;
import com.app.cards.entites.Cards;
import com.app.cards.enums.CardsType;
import com.app.cards.exceptions.CardAlreadyExistsException;
import com.app.cards.exceptions.ResourceNotFoundException;
import com.app.cards.feignclients.LoansFeignClient;
import com.app.cards.repositories.CardsRepository;
import com.app.cards.services.ICardsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    //Working with Constructor Injection
    //@Autowired
    private CardsRepository cardsRepository;
    private LoansFeignClient loansFeignClient;

    @Override
    public String createCard(String mobileNumber) {
        log.info("CardsServiceImpl :: createCard :{}",mobileNumber);
        var card  = cardsRepository.findByMobileNumber(mobileNumber);
        if(card.isPresent()){
            log.error("CardsServiceImpl :: createCard :: Card associated with this Mobile Number already exists: {}",mobileNumber);
            throw new CardAlreadyExistsException("Card associated with this Mobile Number already exists: "+mobileNumber);
        }
        Cards cards = createNewCard(mobileNumber);
        cardsRepository.save(cards);
        return "Card Created Successfully";
    }

    private Cards createNewCard(String mobileNumber) {
        log.info("CardsServiceImpl :: createNewCard :{}",mobileNumber);
        Cards cards = new Cards();
        cards.setCardType("CREDIT_CARD");
        cards.setMobileNumber(mobileNumber);
        cards.setTotalLimit(100000);
        cards.setAmountUsed(0);
        cards.setAvailableAmount(cards.getTotalLimit()-cards.getAmountUsed());
        Integer randomCardNumber =100000000+ new Random().nextInt(999999999);
        cards.setCardNumber(String.valueOf(randomCardNumber));
        return cards;
    }


    @Override
    public CardsDto fetchCard(String mobileNumber) {
        log.info("CardsServiceImpl :: fetchCard :{}",mobileNumber);
        Cards  cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Card not exists with this mobile number: "+mobileNumber));
        log.info("CardsServiceImpl :: fetchCard :: Card data fetched successfully");
        CardsDto cardsDto = new CardsDto();
        BeanUtils.copyProperties(cards, cardsDto);
        return cardsDto;
    }

    @Override
    public Boolean updateCard(CardsDto cardsDto) {
        log.info("CardsServiceImpl :: updateCard :{}",cardsDto.getMobileNumber());
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(()-> new ResourceNotFoundException("Card is not exist with given Card Number"+ cardsDto.getMobileNumber()));
        BeanUtils.copyProperties(cardsDto, cards);
        cardsRepository.save(cards);
        log.info("CardsServiceImpl :: updateCard :: Card updated Successfully : {}",cardsDto.getMobileNumber());
        return true;
    }

    @Override
    public Boolean deleteCard(String mobileNumber) {
        log.info("CardsServiceImpl :: deleteCard :{}",mobileNumber);
        Cards  cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Card not exists with this mobile number: "+mobileNumber));
        cardsRepository.deleteById(cards.getCardId());
        log.info("CardsServiceImpl :: deleteCard :: Card deleted Successfully");
        return true;
    }

	@Override
	public CardsAndLoansDetailsDto fetchCustomerCardsAndLoansDetails(String mobileNumber) {
		log.info("CardsServiceImpl :: fetchCustomerCardsAndLoansDetails :{}", mobileNumber);
		log.info("CardsServiceImpl :: fetchCustomerCardsAndLoansDetails :: Fetching Cards Details");
		Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Card not exists with this mobile number: " + mobileNumber));
		CardsAndLoansDetailsDto cardsAndLoansDetailsDto = new CardsAndLoansDetailsDto();
		BeanUtils.copyProperties(cards, cardsAndLoansDetailsDto);
		log.info("CardsServiceImpl :: fetchCustomerCardsAndLoansDetails :: Fetching Loans Details");
		LoansDto loansDto = loansFeignClient.fetchLoan(mobileNumber);
		setLoansDetails(cardsAndLoansDetailsDto, loansDto);
		return cardsAndLoansDetailsDto;
	}

	private void setLoansDetails(CardsAndLoansDetailsDto cardsAndLoansDetailsDto, LoansDto loansDto) {
		log.info("CardsServiceImpl :: setLoansDetails ::Setting Loans Details in CardsAndLoansDetailsDto");
		if(loansDto != null) {
			cardsAndLoansDetailsDto.setLoansDto(loansDto);
		}else {
			log.error("CardsServiceImpl :: setLoansDetails:: Loans Details is not available");
			throw new ResourceNotFoundException("Loans Details is not available");
		}
	}
}
