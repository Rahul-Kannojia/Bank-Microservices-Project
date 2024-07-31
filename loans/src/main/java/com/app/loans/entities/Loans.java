package com.app.loans.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Loans {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;
	private String mobileNumber;
	private String loanNumber;
	private String loanType;
	private int totalLoan;
	private int amountPaid;
	private int outStandingAmount;
}
