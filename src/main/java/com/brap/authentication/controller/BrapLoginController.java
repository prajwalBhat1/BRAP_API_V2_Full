package com.brap.authentication.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brap.authentication.request.BrapAuthRequest;
import com.brap.authentication.service.AuthService;
@RestController
public class BrapLoginController {
	
	private AuthService authService;

	@ResponseBody
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String login(@RequestBody BrapAuthRequest brapAuthRequest) {
		authService.login(brapAuthRequest);
		return "login successful";
	}
}
