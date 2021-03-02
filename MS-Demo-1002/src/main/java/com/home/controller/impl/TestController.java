package com.home.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TestController {

	@Autowired
	RestTemplate restTemplate;

	private static final String AUTHENTICATION_URL = "http://localhost:1001/authenticate";
	private static final String DEMO1001_URL = "http://localhost:1001/demo1001/users";
	private static final String DEMO1002_URL="http://localhost:1002/demo1002/users";
	private static final String DEMO1004_URL="http://localhost:1004/demo1004/users";

	@RequestMapping(value = "/getResponse", method = RequestMethod.GET)
	public String getResponse() throws JsonProcessingException {

		String response = null;
		try {
			User authenticationUser = getAuthenticationUser();
			String authenticationBody = getBody(authenticationUser);
			HttpHeaders authenticationHeaders = getHeaders();
			HttpEntity<String> authenticationEntity = new HttpEntity<String>(authenticationBody, authenticationHeaders);
			ResponseEntity<ResponseToken> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
					HttpMethod.POST, authenticationEntity, ResponseToken.class);

			System.out.println(authenticationResponse.getStatusCode().equals(HttpStatus.OK));

			if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
				String token = "Bearer " + authenticationResponse.getBody().getToken();
				HttpHeaders headers = getHeaders();
				headers.set("Authorization", token);
				HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
				ResponseEntity<String> demo1001Response = restTemplate.exchange(DEMO1001_URL, HttpMethod.GET, jwtEntity,
						String.class);
				ResponseEntity<String> demo1002Response = restTemplate.exchange(DEMO1002_URL, HttpMethod.GET, jwtEntity,
						String.class);
				System.out.println(demo1002Response.getBody());
				ResponseEntity<String> demo1004Response = restTemplate.exchange(DEMO1004_URL, HttpMethod.GET, jwtEntity,
						String.class);
				System.out.println(demo1004Response.getBody());
				
				if (demo1001Response.getStatusCode().equals(HttpStatus.OK)) {
					response = demo1001Response.getBody();
				}
			}

		} catch (Exception ex) {
			System.out.println("exception" + ex);

		}
		return response;

	}

	private User getAuthenticationUser() {
		User user = new User();
		user.setUsername("DEMO-1001-USER_1");
		user.setPassword("1");
		return user;
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	private String getBody(final User user) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(user);
	}
}