package com.app.cards.entites;

import com.app.cards.enums.CardsType;
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
public class Cards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	private String mobileNumber;
	private String cardNumber;
	private String cardType;
	private int totalLimit;
	private int amountUsed;
	private int availableAmount;
}
