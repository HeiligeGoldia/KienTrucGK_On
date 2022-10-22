package com.se.gktemplate.controller;

import java.util.Arrays;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.se.gktemplate.entity.Book;

@RestController
@Component
public class BookController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@RequestMapping(value = "/template/getBooks")
	public String get(@Value("${url}") String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping(value = "/template/addBook", method = RequestMethod.POST)
	public String create(@Value("${url}") String url, @RequestBody Book book) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Book> entity = new HttpEntity<Book>(book, headers);

		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
	}
	
	@PostMapping("/add")
	public void sendMessage(@RequestBody Book book) throws JMSException, JsonProcessingException {
		jmsTemplate.convertAndSend("inbound.queue", book);
	}
	
	@GetMapping("/get")
	public Book getMessage() {
		return (Book) jmsTemplate.receiveAndConvert("inbound.queue");
	}

}
