package com.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(
		 name = "Cards",
		 description = "Schema to hold Card information"
	   )
public class CardsDto {

	@NotEmpty(message = "Mobile Number can not be a null or empty")
	@Pattern(regexp="(^$|\\d{10})",message = "Mobile Number must be 10 digits")
	@Schema(description = "Mobile Number of Customer", example = "4354437687")
	private String mobileNumber;
	
	@NotEmpty(message = "Card Number can not be a null or empty")
	@Pattern(regexp="(^$|\\d{12})", message = "CardNumber must be 12 digits")
	@Schema(description = "Card Number of the customer",example = "100646930341")
	private String cardNumber;
	
	@NotEmpty(message = "CardType can not be a null or empty")
	@Schema(description = "Type of the card", example = "Credit Card")
	private String cardType;
	
	@Positive(message = "Total card limit should be greater than zero")
	@Schema(description = "Total amount limit available against a card", example = "100000")
	private Integer totalLimit;
	
	@PositiveOrZero(message = "Total amount used should be equal or greater than zero")
	@Schema(description = "Total amount used by a Customer", example = "1000")
	private Integer amountUsed;
	
	@PositiveOrZero(message = "Total available amount should be equal or greater than zero")
	@Schema(description = "Total available amount against a card", example = "90000")
	private Integer availableAmount;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Integer getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(Integer totalLimit) {
		this.totalLimit = totalLimit;
	}

	public Integer getAmountUsed() {
		return amountUsed;
	}

	public void setAmountUsed(Integer amountUsed) {
		this.amountUsed = amountUsed;
	}

	public Integer getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(Integer availableAmount) {
		this.availableAmount = availableAmount;
	}

	@Override
	public String toString() {
		return "CardsDto [mobileNumber=" + mobileNumber + ", cardNumber=" + cardNumber + ", cardType=" + cardType
				+ ", totalLimit=" + totalLimit + ", amountUsed=" + amountUsed + ", availableAmount=" + availableAmount
				+ "]";
	}

	public CardsDto(String mobileNumber, String cardNumber, String cardType, Integer totalLimit, Integer amountUsed,
			Integer availableAmount) {
		super();
		this.mobileNumber = mobileNumber;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.totalLimit = totalLimit;
		this.amountUsed = amountUsed;
		this.availableAmount = availableAmount;
	}

	public CardsDto() {
		super();
	}

}
