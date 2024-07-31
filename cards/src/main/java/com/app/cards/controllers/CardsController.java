package com.app.cards.controllers;

import com.app.cards.dtos.CardsAndLoansDetailsDto;
import com.app.cards.dtos.CardsDto;
import com.app.cards.services.ICardsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Slf4j
public class CardsController {
	@Autowired
	private ICardsService iCardsService;

	@PostMapping(value = "/create")
	public String createCard(@RequestParam String mobileNumber) {
		log.info("CardsController :: createCard : {}", mobileNumber);
		return iCardsService.createCard(mobileNumber);
	}

	@GetMapping(value = "/fetch")
	public CardsDto fetchCard(@RequestParam String mobileNumber) {
		log.info("CardsController :: fetchCard : {}", mobileNumber);
		return iCardsService.fetchCard(mobileNumber);
	}

	@PutMapping(value = "/update")
	public Boolean updateCard(@RequestBody CardsDto cardsDto) {
		log.info("CardsController :: updateCard : {}", cardsDto.getMobileNumber());
		return iCardsService.updateCard(cardsDto);
	}

	@DeleteMapping(value = "/delete")
	public Boolean deleteCard(@RequestParam String mobileNumber) {
		log.info("CardsController :: deleteCard : {}", mobileNumber);
		return iCardsService.deleteCard(mobileNumber);
	}
	
	@GetMapping("/cardsloansdetails")
	public CardsAndLoansDetailsDto fetchCustomerCardsAndLoansDetails(@RequestParam String mobileNumber) {
		log.info("CardsController :: fetchCustomerCardsAndLoansDetails : {}", mobileNumber);
		return iCardsService.fetchCustomerCardsAndLoansDetails(mobileNumber);
	}
}
