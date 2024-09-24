package com.cards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cards extends BaseEntity{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	private String mobileNumber;
	private String cardNumber;
	private String cardType;
	private Integer totalLimit;
	private Integer amountUsed;
	private Integer availableAmount;

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

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
		return "Cards [cardId=" + cardId + ", mobileNumber=" + mobileNumber + ", cardNumber=" + cardNumber
				+ ", cardType=" + cardType + ", totalLimit=" + totalLimit + ", amountUsed=" + amountUsed
				+ ", availableAmount=" + availableAmount + "]";
	}

	public Cards(Long cardId, String mobileNumber, String cardNumber, String cardType, Integer totalLimit, Integer amountUsed,
			Integer availableAmount) {
		super();
		this.cardId = cardId;
		this.mobileNumber = mobileNumber;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.totalLimit = totalLimit;
		this.amountUsed = amountUsed;
		this.availableAmount = availableAmount;
	}

	public Cards() {
		super();
	}

}
