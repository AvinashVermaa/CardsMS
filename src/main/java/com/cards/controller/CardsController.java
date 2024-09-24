package com.cards.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cards.constants.CardsConstants;
import com.cards.dto.CardsDto;
import com.cards.dto.ErrorResponse;
import com.cards.dto.ResponseDto;
import com.cards.service.ICardsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;


@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(
		name = "CRUD REST APIs for Cards in EazyBank",
		description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE card details"
	)
@Validated
public class CardsController {

	private ICardsService cardsService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CardsController.class);
	
	public CardsController(ICardsService cardsService) {
		this.cardsService = cardsService;
		LOGGER.info("CarsController initialized successfully");
	}
	
	
	@Operation(
				summary = "Create Card REST API",
				description = "REST API to create new Card inside EazyBank"
			)
	
	@ApiResponse(
				responseCode = "201",
				description = "HTTP Status CREATED"
			)
	
	@ApiResponse(
				responseCode = "500",
				description = "HTTP Status Integerernal Server Error",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))
			)
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createNewCard(@Valid @RequestParam("mobileNumber") 
	@Pattern(regexp="(^$|\\d{10})",message = "Mobile Number must be 10 digits")
	String mobileNumber){
		
		cardsService.createCard(mobileNumber);
	
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
	}
	
	
	@Operation(
			 summary = "Fetch Card Details REST API",
			 description = "REST API to fetch card details based on a mobile number"
			)
	
	@ApiResponse(
				responseCode = "200",
				description = "HTTP Status OK"
			)
	
	@ApiResponse(
				responseCode = "500",
				description = "HTTP Status Integerernal Server Error",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))
			
			)
	
	@GetMapping("/fetch")
	public ResponseEntity<CardsDto> fetchCardDetails(@Valid @RequestParam("mobileNumber") 
	@Pattern(regexp="(^$|\\d{10})",message = "Mobile Number must be 10 digits")
	String mobileNumber){
		
		CardsDto cardsDto = cardsService.fetchCard(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(cardsDto);
	}
	
	@Operation(
			 summary = "Delete Card Details REST API",
			 description = "REST API to delete Card details based on a mobile number"
			)
	
	@ApiResponse(
				responseCode = "200",
				description = "HTTP Status OK"
			)
	
	@ApiResponse(
				responseCode = "500",
				description = "HTTP Status Integerernal Server Error",
				content = @Content(schema=@Schema(implementation=ErrorResponse.class))
			)
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteCardDetails(@Valid @RequestParam("mobileNumber") 
	@Pattern(regexp="(^$|\\d{10})",message = "Mobile Number must be 10 digits")
	String mobileNumber){
		
		boolean isDeleted = cardsService.deleteCard(mobileNumber);
		if(isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(CardsConstants.STATUS_500, CardsConstants.MESSAGE_500));
		}
	}
	
	@Operation(
				summary = "Update Card Details REST API",
				description = "REST API to update card details based on a card number"
			)
	
	@ApiResponse(
				responseCode = "200",
				description = "HTTP Status OK"
			)
	
	@ApiResponse(
				responseCode = "500",
				description = "Expectation Failed",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))
			)
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateCardsDetails(@Valid @RequestBody CardsDto cardsDto){
		boolean isUpdated = cardsService.updateCard(cardsDto);
		if(isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(CardsConstants.STATUS_500, CardsConstants.MESSAGE_500));
		}
	}
	
}
