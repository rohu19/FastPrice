package com.example.FastPrice;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FastPriceService {
	// here setting first third party URL
	private static final String AMAZON_URL = "https://price-amazon.free.beeceptor.com/service/{product}/price";
	// here setting second third party URL
	private static final String FLIPKART_URL = "https://price-flipkart.free.beeceptor.com/service/{product}/price";

//Assign restTemplate
	@Autowired
	private RestTemplate restTemplate;
//here assign timeout slot
	private int timeout;

	// this line to assing help to all crud methods performs
	@Autowired
	private FastPriceRepository fastPriceRepository;

	// this method Assign two third party URL price
	public FastPrice getFastPrice(String product, Long userId) {
		String amazonUrl = AMAZON_URL.replace("{product}", product);
		String flipkartUrl = FLIPKART_URL.replace("{product}", product);

		long start = System.currentTimeMillis();
		ResponseEntity<FastPrice> amazonResponse = restTemplate.getForEntity(amazonUrl, FastPrice.class);
		ResponseEntity<FastPrice> flipkartResponse = restTemplate.getForEntity(flipkartUrl, FastPrice.class);
		long end = System.currentTimeMillis();

		FastPrice fastPrice = getFastPriceResponse(amazonResponse, flipkartResponse);
		if (fastPrice != null) {
			fastPrice.setProductCode(product);
			fastPrice.setResponseTime(start);
			fastPrice.setId(userId);
			fastPrice.setResponseTime(end);
			fastPriceRepository.save(fastPrice);
		}
		return fastPrice;
	}

	// this methode compare to prices
	private FastPrice getFastestPrice(FastPrice price1, FastPrice price2) {
		// Compare the response time of the two prices and get the fastest one
		return price1.getResponseTime() < price2.getResponseTime() ? price1 : price2;
	}

//here this method return fastest price value 
	public FastPrice getFastPriceResponse(ResponseEntity<FastPrice> amazonResponse,
			ResponseEntity<FastPrice> flipkartResponse) {
		if (amazonResponse.getStatusCode().is2xxSuccessful() && flipkartResponse.getStatusCode().is2xxSuccessful()) {
			FastPrice amazonPrice = amazonResponse.getBody();
			FastPrice flipkartPrice = flipkartResponse.getBody();

			if (amazonPrice.getPrice() < flipkartPrice.getPrice()) {
				return amazonPrice;
			} else {
				return flipkartPrice;
			}
		}
		return null;
	}

	// this only for sending data to dataBase
	public FastPrice save(FastPrice fastPrice) {
		return fastPriceRepository.save(fastPrice);
	}

	// this method implement a timeout on the FastPrice API of 30 seconds
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		int timeoutMillis = timeout * 30000;
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(timeoutMillis);
		factory.setReadTimeout(timeoutMillis);
		return factory;
	}

}
