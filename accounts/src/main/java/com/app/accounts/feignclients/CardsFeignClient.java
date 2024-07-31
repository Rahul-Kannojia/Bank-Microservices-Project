package com.app.accounts.feignclients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.accounts.dtos.CardsDto;


@FeignClient(name = "CARDS", fallback = CardsFallback.class)
@LoadBalancerClient(name = "CARDS")
public interface CardsFeignClient {

	@GetMapping(value = "/api/fetch")
	public CardsDto fetchCardDetails(@RequestParam String mobileNumber);
}
