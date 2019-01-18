package com.brap.authentication.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brap.authentication.request.BrapAuthRequest;
import com.brap.authentication.response.AuthResponseView;
import com.brap.authentication.service.AuthService;
import com.brap.common.exception.InvalidPasswordException;
import com.brap.common.exception.UserNameDoesNotExistException;
import com.brap.persistence.entity.BrapUserInfo;
import com.brap.schedule.persistence.repo.BrapUserInfoRepo;

@Service
public class AuthServiceImpl implements AuthService {

	private BrapUserInfoRepo userInfoRepo;

	private PasswordEncoder encoder;
    
	@Lazy
	@Autowired
	public AuthServiceImpl(BrapUserInfoRepo userInfoRepo, PasswordEncoder encoder) {
		this.userInfoRepo = userInfoRepo;
		this.encoder = encoder;
	}

	@Override
	public AuthResponseView login(BrapAuthRequest authRequest) {
		Optional<BrapUserInfo> userInfo = userInfoRepo.findById(authRequest.getUserName());
		if (!userInfo.isPresent()) {
			throw new UserNameDoesNotExistException("UserName not found !!!",
					"Please register with us before you try to login!!");
		}
		// TO-DO Use any encryption-decryption algorithm here
		if (!encoder.matches(authRequest.getPassword(),userInfo.get().getPassword())) {
			throw new InvalidPasswordException("Invalid Password entered!", "Please enter a valid password!!");
		}
		return buildAuthResponseView(userInfo.get());
	}

	private AuthResponseView buildAuthResponseView(BrapUserInfo userInfo) {
		AuthResponseView authResponseView = new AuthResponseView();
		authResponseView.setId(userInfo.getId());
		return null;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
