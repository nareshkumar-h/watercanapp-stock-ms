package com.revature.watercanappstockms.controller;

import java.util.List;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.watercanappstockms.exception.ServiceException;
import com.revature.watercanappstockms.model.Stock;
import com.revature.watercanappstockms.service.StockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class StockController {

	@Autowired
	StockService stock;

	@GetMapping("/viewStock")
	@ApiOperation("viewStock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Viewed Successfully", response = Stock.class),
			@ApiResponse(code = 400, message = "View failed") })
	public ResponseEntity<?> viewStock() {

		List<Stock> list = null;
		String errorMessage = null;
		try {
			list = stock.viewStock();
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}
		if (list != null)
			return new ResponseEntity<>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	// @PutMapping("/update")
	@PostMapping("/update")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Updated Successfully", response = Message.class),
			@ApiResponse(code = 400, message = "Update failed") })
	public ResponseEntity<?> update(@RequestParam("updatecans") int updatecans) {

		String errorMessage = null;
		String Message = null;

		try {
			stock.updateCans(updatecans);
			Message = "Updated Successfully";
		} catch (ServiceException e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}

		if (Message != null)
			return new ResponseEntity<>(Message, HttpStatus.OK);
		else
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

	}

}
