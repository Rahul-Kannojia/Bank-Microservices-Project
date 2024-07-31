package com.app.accounts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	/**
	 * @Data- Don't use
	 * @ToString- Don't use
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;
	@Column(name = "customer_name", nullable = false)
	private String customerName;
	@Column(name = "customer_email")
	private String email;
	@Column(name = "mobile_number", nullable = false)
	private String mobileNumber;
}
