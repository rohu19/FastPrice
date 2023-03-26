package com.example.FastPrice;

import java.security.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FastPriceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FastPriceApplication.class, args);
	}

	@Autowired
	private FastPriceService fastPriceService;

	
	//here we are setting data throw commandLine Argument
	
	
	@Override
	public void run(String... args) throws Exception {
		FastPrice fastPrice = new FastPrice();
		fastPrice.setId(1258L);
		fastPrice.setProductCode("kaka");
		fastPrice.setProduct("Iphone1");
		fastPrice.setPrice(500000);
		fastPrice.setSource("Amazon");
		fastPrice.setUserId("Ro245");
		fastPrice.setTimestamp("2023-03-25T04:20:10");
		fastPrice.setResponseTime(30.8);
/*****************************************************************************/
		FastPrice fastPrice1 = new FastPrice();
		fastPrice1.setId(475L);
		fastPrice1.setProductCode("kaki");
		fastPrice1.setProduct("Iphone2");
		fastPrice1.setPrice(600000);
		fastPrice1.setSource("FlipKart");
		fastPrice1.setUserId("Ro200");
		fastPrice1.setTimestamp("2023-03-25T10:30:00");
		fastPrice.setResponseTime(20.5);

	}

}
