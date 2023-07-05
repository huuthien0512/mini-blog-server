package com.miniblogserver.admin;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miniblogserver.dto.RoleDTO;
import com.miniblogserver.dto.UserDTO;
import com.miniblogserver.service.RoleService;
import com.miniblogserver.service.UserService;

@RestController
@RequestMapping("api/v1/admin/users")
public class UserManagementController {

	private final UserService userService;

	private final RoleService roleService;

	public UserManagementController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping()
	public List<UserDTO> getUserByUsername(@RequestParam("keywords") String keywords) {
		return userService.getUserByUsername(keywords);
	}

	@GetMapping("/{id}")
	public UserDTO getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@GetMapping(value = "/roles")
	public List<RoleDTO> getRoles() {
		return roleService.getRoles();
	}

	@PutMapping()
	public void updateUserEnabled(@RequestBody Map<String, Object> data) {
		Long id = Long.parseLong(data.get("id").toString());
		Boolean enabled = Boolean.parseBoolean(data.get("enabled").toString());
		userService.updateUserEnabled(id, enabled);
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
	}
}
