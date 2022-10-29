package com.se.th07.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.se.th07.entity.User;

@RestController
public class UserRestController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/template/getUsers/{uid}")
	public String get(@PathVariable("uid") String uid) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8083/getUser/" + uid, HttpMethod.GET, entity, String.class)
				.getBody();
	}

	@RequestMapping(value = "/template/create", method = RequestMethod.POST)
	public String create(@RequestBody User u) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<User>(u, headers);

		return restTemplate.exchange("http://localhost:8083/register", HttpMethod.POST, entity, String.class).getBody();
	}

	@RequestMapping(value = "/template/update/{uid}", method = RequestMethod.PUT)
	public String update(@PathVariable("uid") String uid, @RequestBody User User) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<User>(User, headers);

		return restTemplate.exchange("http://localhost:8083/updateUser/" + uid, HttpMethod.PUT, entity, String.class).getBody();
	}

	@RequestMapping(value = "/template/delete/{uid}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("uid") String uid) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<User>(headers);

		return restTemplate.exchange("http://localhost:8083/deleteUser/" + uid, HttpMethod.DELETE, entity, String.class).getBody();
	}

}
