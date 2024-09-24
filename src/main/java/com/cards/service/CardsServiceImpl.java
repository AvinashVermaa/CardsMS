package com.cards.service;

import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cards.constants.CardsConstants;
import com.cards.dto.CardsDto;
import com.cards.entity.Cards;
import com.cards.exception.CardAlreadyExistsException;
import com.cards.exception.ResourceNotFoundException;
import com.cards.mapper.CardMapper;
import com.cards.repository.CardsRepository;

@Service
public class CardsServiceImpl implements ICardsService {

	private CardsRepository cardsRepo;

	private static final String CARDS = "Cards";

	private static final Random random = new Random();

	private static final Logger LOGGER = LoggerFactory.getLogger(CardsServiceImpl.class);

	public CardsServiceImpl(CardsRepository cardsRepo) {
		this.cardsRepo = cardsRepo;
		LOGGER.info("CardsServiecImpl initialized successfully");
	}

	@Override
	public void createCard(String mobileNumber) {
		Optional<Cards> optional = cardsRepo.findByMobileNumber(mobileNumber);
		if (optional.isPresent()) {
			throw new CardAlreadyExistsException(
					"Card already registered in the system having mobileNumber : " + mobileNumber);
		}

		cardsRepo.save(createNewCard(mobileNumber));
	}

	private Cards createNewCard(String mobileNumber) {
		Cards cards = new Cards();
		long randomCardNumber = 100000000000L + random.nextInt(900000000);
		cards.setCardNumber(Long.toString(randomCardNumber));
		cards.setAmountUsed(0);
		cards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
		cards.setCardType(CardsConstants.CREDIT_CARD);
		cards.setMobileNumber(mobileNumber);
		cards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);

		return cards;
	}

	@Override
	public CardsDto fetchCard(String mobileNumber) {
		Cards cards1 = cardsRepo.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException(CARDS, "mobileNumber", mobileNumber));

		return CardMapper.mapToCardsDto(new CardsDto(), cards1);
	}

	@Override
	public boolean updateCard(CardsDto cardsDto) {
		boolean isUpdated = false;
		Cards cards1 = cardsRepo.findByCardNumber(cardsDto.getCardNumber())
				.orElseThrow(() -> new ResourceNotFoundException(CARDS, "cardNumber", cardsDto.getCardNumber()));

		Cards cards2 = CardMapper.mapToCards(cardsDto, cards1);

		cardsRepo.save(cards2);
		isUpdated = true;

		return isUpdated;
	}

	@Override
	public boolean deleteCard(String mobileNumber) {
		boolean isDeleted = false;

		Cards cards = cardsRepo.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException(CARDS, "mobileNumber", mobileNumber));

		cardsRepo.deleteById(cards.getCardId());
		isDeleted = true;
		return isDeleted;
	}

}
