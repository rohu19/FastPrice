package com.example.FastPrice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app")
public class FastPriceController {

	@Autowired
	private FastPriceService fastPriceService;

//this method sending data to database
	@PostMapping("save")
	public ResponseEntity<FastPrice> save(@RequestBody FastPrice fastPrice) {
		return ResponseEntity.ok(fastPriceService.save(fastPrice));
	}

	/*******************************************************************************/
	// This method fetching data to database and also reading header data
	@GetMapping("/{product}/price")
	public ResponseEntity<FastPrice> getFastPrice(@PathVariable String product,
			@RequestHeader(value = "X-User") Long userId) {
		FastPrice fastPrice = fastPriceService.getFastPrice(product, userId);
		if (fastPrice == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(fastPrice, HttpStatus.OK);
	}

}
