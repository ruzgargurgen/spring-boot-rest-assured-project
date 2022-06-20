package com.tr.restassured.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tr.restassured.exceptions.UserNotFoundException;
import com.tr.restassured.utils.FactoryUtils;

@RestController
public class TestApi {

	private static final Logger logger = LoggerFactory.getLogger(TestApi.class);

	@PostMapping("/add")
	public ResponseEntity<Result> add(@RequestHeader() MultiValueMap<String, String> headers,
			@RequestParam("params") List<Integer> values) throws Exception {

		String username = getUserInfo(headers);
		Integer value = Optional//
				.ofNullable(values).orElseGet(Collections::emptyList).stream().reduce((a, b) -> a + b).get();
		return ResponseEntity.ok(new Result(username, value));
	}
	
	@PostMapping("/multiplication")
	public ResponseEntity<Result> multiplication(@RequestHeader() MultiValueMap<String, String> headers,
			@RequestParam("params") List<Integer> values) throws Exception {

		String username = getUserInfo(headers);
		Integer value = Optional//
				.ofNullable(values).orElseGet(Collections::emptyList).stream().reduce((a, b) -> a * b).get();
		return ResponseEntity.ok(new Result(username, value));
	}

	@PostMapping("/division")
	public ResponseEntity<Result> division(
			@RequestHeader()MultiValueMap<String, String>headers,
			@RequestParam("params")List<Integer> values) throws Exception{
		logger.info("division metod call ..");
		String username=getUserInfo(headers);
		Integer value=Optional
		.ofNullable(values)
		.orElseGet(Collections::emptyList)
		.stream()
		.reduce((a,b)->a/b).get();
		return ResponseEntity.ok(new Result(username,value));
	}
	
	@GetMapping("/sum")
	public ResponseEntity<Result> sum(
			@RequestHeader()MultiValueMap<String, String>headers,
			@RequestParam("param")Integer value) throws Exception{
		logger.info("sum metod call ..");

		return ResponseEntity.ok(new Result(getUserInfo(headers),FactoryUtils.factorial(value)));
	}


	private String getUserInfo(MultiValueMap<String, String> headers) throws Exception {
		String username = headers.getFirst("username");
		String password = headers.getFirst("password");
		if (username == null || password == null)
			throw new UserNotFoundException("User info not found");

		return String.format("{username:%s ,password:%s}",username,password);

	}

	@ExceptionHandler(value = { UserNotFoundException.class })
	public ResponseEntity<Object> handleException(UserNotFoundException ex) {
		logger.error("",ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = { ArithmeticException.class })
	public ResponseEntity<Object> handleArithmeticException(ArithmeticException ex) {
		logger.error("",ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}


}