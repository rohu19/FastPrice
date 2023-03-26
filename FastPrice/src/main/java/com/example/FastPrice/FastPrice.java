package com.example.FastPrice;

import java.security.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FastPrice {
//here assing All Entity clss and creating  getter setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String productCode;

	private String product;

	private double price;

	private String source;

	private String userId;

	private String timestamp;

	private double responseTime;

}

/*
 * to testing purpose setting URL -> http://localhost:9095/app/save
 * 
 * 
 * { "id":12, "productCode":"asd", "product":"Iphone", "price":50000,
 * "source":"Amazon", "userId":"547", "timestamp":"2023-03-25T10:30:00",
 * "responseTime":30.0,
 * 
 * } { "id":13, "productCode":"kaka", "price":10000, "source":"Flipkart",
 * "userId":"540", "timestamp":"2023-03-25T40:30:00", "responseTime":20.0,
 * 
 * }
 */

// "responseTime": "2023-03-25T10:30:00"

/*
 * The Application Flow is working on like this. first-> Controller,
 * second->Service,Third->Repository, fourth->DataBase
 * -----------------------------------------------------------------------------
 *  This project involves building a Spring Boot application that
 * provides a RESTful API called FastPrice that obtains the fastest price for a
 * given product from two different third-party APIs and stores the result in a
 * MySQL database. Additionally, the FastPrice API has a timeout of 30 seconds.
 * 
 * instructions and Advance Rest Client collection for API testing.
 * 
 * 
 * 
 * 
 * 
 * 
 */