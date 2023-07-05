package com.miniblogserver.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miniblogserver.dto.UserCreationDTO;
import com.miniblogserver.dto.UserDTO;
import com.miniblogserver.model.User;
import com.miniblogserver.repository.UserRepository;
import com.miniblogserver.utils.Util;

@Transactional
@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	
	private final RoleService roleService;
	
	private final static String DEFAULT_IMAGE = "https://upload.wikimedia.org/wikipedia/commons/5/50/User_icon-cp.svg";
	
	@Autowired
	PasswordEncoder passwordEncoder;


	public UserService(UserRepository userRepository, RoleService roleService) {
		this.userRepository = userRepository;
		this.roleService = roleService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			return new User();
		}
		return user;
	}

	public List<UserDTO> getUserByUsername(String username) {
		User me = Util.getCurrentUser();
		List<User> users = userRepository.findUsersByUsername(username, me.getId());
		List<UserDTO> userDTO = new ArrayList<>(users.size());
		users.forEach(user -> userDTO.add(convertToDTO(user)));
		return userDTO;
	}

	public int updateUserEnabled(Long userId, Boolean enabled) {
		return userRepository.updateUserEnabledByUserId(userId, enabled);
	}

	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);
	}

	public UserDTO getUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		UserDTO userDTO = null;
		if (optionalUser.isPresent()) {
			userDTO = convertToDTO(optionalUser.get());
		}
		return userDTO;
	}
	
	public void register(UserCreationDTO userCreationDTO) throws Exception {
		User user = userRepository.findUserByUsername(userCreationDTO.getUsername());
		if (user != null) {
			throw new Exception("User already exists");
		}
		user = new User();
		String username = userCreationDTO.getUsername();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));
		user.setNickname(username);
		user.setEnabled(true);
		StringBuilder email = new StringBuilder();
		email.append(username).append("@gmail.com");
		user.setEmail(email.toString());
		user.setUserface(DEFAULT_IMAGE);
		user.setRegTime(new Timestamp(System.currentTimeMillis()));
		User savedUser = userRepository.save(user);
		roleService.setUserRole(savedUser.getId(), 2L);
	}

	public UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setNickname(user.getNickname());
		userDTO.setEnabled(user.isEnabled());
		userDTO.setRoleName(user.getRoleUser().getRole().getName());
		userDTO.setEmail(user.getEmail());
		userDTO.setUserface(user.getUserface());
		userDTO.setRegTime(user.getRegTime());
		return userDTO;
	}

	public User convertToEntity(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setUsername(userDTO.getUsername());
		user.setEnabled(userDTO.isEnabled());
		user.setEmail(userDTO.getEmail());
		user.setUserface(userDTO.getUserface());
		user.setRegTime(userDTO.getRegTime());
		return user;
	}
}
