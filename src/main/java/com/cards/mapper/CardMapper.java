package com.cards.mapper;

import com.cards.dto.CardsDto;
import com.cards.entity.Cards;

public final class CardMapper {

	private CardMapper() {
		//restrict the initialization:
	}
	
	public static CardsDto mapToCardsDto(CardsDto dto,Cards cards) {
		dto.setAmountUsed(cards.getAmountUsed());
		dto.setAvailableAmount(cards.getAvailableAmount());
		dto.setCardNumber(cards.getCardNumber());
		dto.setCardType(cards.getCardType());
		dto.setMobileNumber(cards.getMobileNumber());
		dto.setTotalLimit(cards.getTotalLimit());
		
		return dto;
	}
	
	public static Cards mapToCards(CardsDto dto,Cards cards) {
		cards.setAmountUsed(dto.getAmountUsed());
		cards.setAvailableAmount(dto.getAvailableAmount());
		cards.setCardNumber(dto.getCardNumber());
		cards.setCardType(dto.getCardType());
		cards.setMobileNumber(dto.getMobileNumber());
		cards.setTotalLimit(dto.getTotalLimit());
		
		return cards;
	}
}
