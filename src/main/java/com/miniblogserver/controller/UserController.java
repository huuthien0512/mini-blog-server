package com.miniblogserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniblogserver.dto.UserCreationDTO;
import com.miniblogserver.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping()
    public void register(@RequestBody UserCreationDTO userCreationDTO) throws Exception {
        userService.register(userCreationDTO);
    }
}
