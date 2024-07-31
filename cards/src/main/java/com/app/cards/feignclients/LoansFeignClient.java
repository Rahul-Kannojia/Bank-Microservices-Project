package com.app.cards.feignclients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.cards.dtos.LoansDto;


@FeignClient(name = "LOANS",fallback = LoansFallback.class)
@LoadBalancerClient(name = "LOANS")
public interface LoansFeignClient {

	@GetMapping(value = "/api/fetch")
	 public LoansDto fetchLoan(@RequestParam String mobileNumber);
}
